package com.ivyapps.composematerial3helper.domain

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.ivyapps.composematerial3helper.*
import com.ivyapps.composematerial3helper.content.*
import com.ivyapps.composematerial3helper.domain.data.MaterialComponent
import com.ivyapps.composematerial3helper.domain.data.MaterialComponentsGroup

@Service(Service.Level.PROJECT)
class MaterialComponentsService(project: Project) {
    val content by lazy {
        buildList {
            common()
            composeRuntime()
            animations()
            buttons()
            fab()
            iconButtons()
            datePickers()
            badges()
            bottomAppBar()
            bottomSheets()
            cards()
            checkboxes()
            chips()
            dialogs()
            dividers()
            lists()
            menus()
            navigationBars()
            navigationDrawers()
            navigationRails()
            progressIndicators()
            radioButtons()
            searchBars()
            sliders()
            snackbars()
            switches()
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