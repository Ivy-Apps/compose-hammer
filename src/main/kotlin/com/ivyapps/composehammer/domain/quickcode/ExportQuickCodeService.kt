package com.ivyapps.composehammer.domain.quickcode

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService
import com.ivyapps.composehammer.persistence.QuickCodeConfigurationJson
import java.io.File


@Service(Service.Level.PROJECT)
class ExportQuickCodeService(
    private val project: Project
) {
    companion object {
        private const val OUTPUT_FILE_NAME = "compose_hammer_quickcode_conf.json"
    }

    fun export() {
        chooseDirectoryAndExport()
    }

    private fun chooseDirectoryAndExport() {
        val descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
        descriptor.description = "Choose Directory to Export File"
        FileChooser.chooseFile(descriptor, project, null) { chosenDir: VirtualFile ->
            val content = configurationJson()
            exportStringToFile(chosenDir, content)
        }
    }

    private fun configurationJson(): String {
        val quickCodeService = project.service<QuickCodeService>()
        return QuickCodeConfigurationJson().toString(quickCodeService.configuration)
    }

    private fun exportStringToFile(chosenDir: VirtualFile, content: String) {
        try {
            val file = File(chosenDir.path, OUTPUT_FILE_NAME)
            file.writeText(content)
            Messages.showInfoMessage("File exported successfully.", "Success")
        } catch (e: Exception) {
            Messages.showErrorDialog("Failed to export file: ${e.localizedMessage}", "Error")
        }
    }
}