package com.ivyapps.composehammer.domain

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.ivyapps.composehammer.*
import com.ivyapps.composehammer.content.*
import com.ivyapps.composehammer.domain.data.MaterialComponent
import com.ivyapps.composehammer.domain.data.MaterialComponentsGroup

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
            textFields()
            cards()
            checkboxes()
            switches()
            radioButtons()
            sliders()
            bottomSheets()
            dialogs()
            badges()
            lists()
            dividers()
            chips()
            menus()
            topAppBars()
            tabs()
            navigationBars()
            navigationDrawers()
            navigationRails()
            bottomAppBar()
            progressIndicators()
            snackbars()
            datePickers()
            timePickers()
            searchBars()
            tooltips()
        }
    }

    fun findGroupByTitle(groupTitle: String): MaterialComponentsGroup {
        return requireNotNull(content.find {
            it.title == groupTitle || it.shortTitle == groupTitle
        }) {
            "Couldn't find '$groupTitle' group in $content!!!"
        }
    }

    fun findComponentByNameInGroup(
        group: MaterialComponentsGroup,
        componentName: String
    ): MaterialComponent {
        return requireNotNull(group.components.find {
            it.name == componentName || it.shortName == componentName
        }) {
            "Couldn't find '$componentName' component in ${group.components}!!!"
        }
    }
}