package com.ivyapps.composematerial3helper.services

import com.ivyapps.composematerial3helper.buttons
import com.ivyapps.composematerial3helper.common
import com.ivyapps.composematerial3helper.data.MaterialComponent
import com.ivyapps.composematerial3helper.data.MaterialComponentsGroup
import com.ivyapps.composematerial3helper.fab
import com.ivyapps.composematerial3helper.iconButtons
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MaterialComponentsService(project: Project) {
    val content by lazy {
        buildList {
            common()
            buttons()
            fab()
            iconButtons()

            group("Navigation") {

            }
        }
    }

    fun findGroupByTitle(groupTitle: String): MaterialComponentsGroup {
        return requireNotNull(content.find { it.title == groupTitle }) {
            "Couldn't find '$groupTitle' group in $content!!!"
        }
    }

    fun findComponentByNameInGroup(
        group: MaterialComponentsGroup,
        componentName: String
    ): MaterialComponent {
        return requireNotNull(group.components.find { it.name == componentName }) {
            "Couldn't find '$componentName' component in ${group.components}!!!"
        }
    }
}