package com.ivyapps.composehammer.domain.quickcode

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.ivyapps.composehammer.domain.data.quickcode.QuickCodeConfiguration
import com.ivyapps.composehammer.domain.ui.generateImportsCode
import com.ivyapps.composehammer.persistence.QuickCodeConfigurationJson
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
            Messages.showErrorDialog("Failed to import file: ${e.localizedMessage}", "Error")
        }
    }

    private fun processImportedString(quickCodeJson: String) {
        try {
            val configuration = QuickCodeConfigurationJson().fromString(quickCodeJson)
            requireNotNull(configuration)
            importConfiguration(configuration)
            Messages.showInfoMessage(quickCodeJson, "Import successful")
        } catch (e: Exception) {
            Messages.showErrorDialog(
                """
                    Error: ${e.message ?: "Unknown"}.
                    Failed to parse: \"$quickCodeJson\".
                """.trimIndent(),
                "Error"
            )
        }
    }

    private fun importConfiguration(configuration: QuickCodeConfiguration) {
        val service = project.service<QuickCodeService>()

        for (group in configuration.groups) {
            val newGroup = service.addGroup(rawName = group.name)
                ?: continue
            group.codeItems.forEach { codeItem ->
                service.addCodeItem(
                    group = newGroup,
                    rawName = codeItem.name,
                    rawImports = generateImportsCode(codeItem.imports) ?: "",
                    rawCode = codeItem.code
                )
            }
        }
    }
}
