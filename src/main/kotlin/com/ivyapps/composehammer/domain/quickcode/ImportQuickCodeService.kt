package com.ivyapps.composehammer.domain.quickcode

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.ivyapps.composehammer.domain.data.quickcode.QuickCodeConfiguration
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService
import com.ivyapps.composehammer.persistence.QuickCodeConfigurationJson
import com.ivyapps.composehammer.showErrorToast
import com.ivyapps.composehammer.showInfoToast
import java.io.File

@Service(Service.Level.PROJECT)
class ImportQuickCodeService(
    private val project: Project,
) {
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
            showInfoToast(
                title = "Import successful",
                message = quickCodeJson,
            )
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
        val service = project.service<QuickCodeService>()

        // TODO: Implement
//        for (group in configuration.groups) {
//            val newGroup = service.addGroup(rawName = group.name)
//                ?: continue
//            group.codeItems.forEach { codeItem ->
//                service.addCodeItem(
//                    group = newGroup,
//                    rawName = codeItem.name,
//                    rawImports = generateImportsCode(codeItem.imports) ?: "",
//                    rawCode = codeItem.code
//                )
//            }
//        }
    }
}
