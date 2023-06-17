package com.ivyapps.composehammer.domain.quickcode

import com.intellij.openapi.components.Service
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import java.io.File


@Service(Service.Level.PROJECT)
class ExportQuickCodeService(
    private val project: Project
) {

    fun export() {
        chooseDirectoryAndExport()
    }

    private fun chooseDirectoryAndExport() {
        val descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
        descriptor.description = "Choose Directory to Export File"
        FileChooser.chooseFile(descriptor, project, null) { chosenDir: VirtualFile ->
            val content = getStringContent()
            exportStringToFile(chosenDir, content)
        }
    }

    private fun getStringContent(): String {
        // Fetch or generate your string content here.
        return "Hello, World!"
    }

    private fun exportStringToFile(chosenDir: VirtualFile, content: String) {
        try {
            val file = File(chosenDir.path, "exported_file.txt")
            file.writeText(content)
            Messages.showInfoMessage("File exported successfully.", "Success")
        } catch (e: Exception) {
            Messages.showErrorDialog("Failed to export file: ${e.localizedMessage}", "Error")
        }
    }
}