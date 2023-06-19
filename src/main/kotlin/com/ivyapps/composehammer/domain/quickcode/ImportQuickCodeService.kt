package com.ivyapps.composehammer.domain.quickcode

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.ivyapps.composehammer.domain.data.Either
import com.ivyapps.composehammer.domain.data.Reorderable
import com.ivyapps.composehammer.domain.data.fold
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.data.quickcode.QCProject
import com.ivyapps.composehammer.domain.data.quickcode.QuickCodeConfiguration
import com.ivyapps.composehammer.domain.quickcode.service.AddOperationResult
import com.ivyapps.composehammer.domain.quickcode.service.BaseOperations
import com.ivyapps.composehammer.domain.quickcode.service.EditOperationResult
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService.*
import com.ivyapps.composehammer.domain.ui.generateImportsCode
import com.ivyapps.composehammer.persistence.QuickCodeConfigurationJson
import com.ivyapps.composehammer.showErrorToast
import com.ivyapps.composehammer.showInfoToast
import java.io.File

@Service(Service.Level.PROJECT)
class ImportQuickCodeService(
    private val project: Project,
) {
    private val service = project.service<QuickCodeService>()


    fun import() {
        chooseFileAndImport()
    }

    private fun chooseFileAndImport() {
        val descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
        descriptor.description = "Choose File to Import"
        FileChooser.chooseFile(descriptor, project, null) { chosenFile: VirtualFile ->
            importStringFromFile(chosenFile)
        }
    }

    private fun importStringFromFile(chosenFile: VirtualFile) {
        try {
            val file = File(chosenFile.path)
            require(file.name.endsWith(".json")) {
                "Not a JSON file."
            }
            val content = file.readText()
            processImportedString(content)
        } catch (e: Exception) {
            showErrorToast(message = "Failed to import file: ${e.localizedMessage}")
        }
    }

    private fun processImportedString(quickCodeJson: String) {
        try {
            val configuration = QuickCodeConfigurationJson().fromString(quickCodeJson)
            requireNotNull(configuration)
            importConfiguration(configuration)
        } catch (e: Exception) {
            showErrorToast(
                message = """
                    Error: ${e.message ?: "Unknown"}.
                    Failed to parse: \"$quickCodeJson\".
                """.trimIndent(),
            )
        }
    }

    private fun importConfiguration(configuration: QuickCodeConfiguration) {
        with(service) {
            val result = configuration.projects.flatMap {
                val projectOps = ProjectOps()
                projectOps.importProject(it)
            }
            showInfoToast(
                title = when {
                    result.all { it is Either.Right } -> "Import Successful"
                    result.any { it is Either.Left } -> "Partial Success"
                    else -> "Import Failed"
                },
                message = result
                    .sortedBy { it is Either.Left }
                    .joinToString(separator = "<br>") {
                        when (it) {
                            is Either.Left -> "Failure: ${it.error}"
                            is Either.Right -> "Success: ${it.value}"
                        }
                    }
            )
        }
    }

    private fun ProjectOps.importProject(project: QCProject): List<Either<String, CodeItem>> {
        val input = ProjectInput(
            rawName = project.name
        )
        return import(project, input).fold(
            mapLeft = {
                listOf(Either.Left(it))
            },
            mapRight = { importedProject ->
                val groupOps = service.CodeGroupOps(importedProject)
                importedProject.groups.flatMap {
                    groupOps.importGroup(it)
                }
            }
        )
    }

    private fun CodeGroupOps.importGroup(group: CodeGroup): List<Either<String, CodeItem>> {
        val input = CodeGroupInput(
            rawName = group.name
        )
        return import(group, input).fold(
            mapLeft = {
                listOf(Either.Left(it))
            },
            mapRight = { importedGroup ->
                val codeItemsOps = service.CodeItemOps(project, importedGroup)
                importedGroup.codeItems.map {
                    codeItemsOps.importCodeItem(it)
                }
            }
        )
    }

    private fun CodeItemOps.importCodeItem(codeItem: CodeItem): Either<String, CodeItem> {
        val input = CodeItemInput(
            rawName = codeItem.name,
            rawImports = generateImportsCode(codeItem.imports) ?: "",
            rawCode = codeItem.codeTemplate,
        )
        return import(codeItem, input)
    }

    private fun <I, T : Reorderable> BaseOperations<I, T>.import(
        item: T,
        input: I,
    ): Either<String, T> {
        return when (val res = addItem(input)) {
            is AddOperationResult.Added -> Either.Right(item)
            is AddOperationResult.AlreadyExists -> {
                when (val editRes = editItem(
                    item = item,
                    input = input
                )) {
                    is EditOperationResult.Invalid -> Either.Left(
                        "Failed to import $item because ${editRes.reason}."
                    )

                    is EditOperationResult.Updated -> Either.Right(item)
                }
            }

            is AddOperationResult.Invalid -> {
                Either.Left("Failed to import $item because ${res.reason}.")
            }
        }
    }
}
