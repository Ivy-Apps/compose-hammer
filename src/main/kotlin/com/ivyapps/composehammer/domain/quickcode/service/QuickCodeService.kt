package com.ivyapps.composehammer.domain.quickcode.service

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.data.quickcode.QCProject
import com.ivyapps.composehammer.domain.data.quickcode.QuickCodeConfiguration
import com.ivyapps.composehammer.domain.quickcode.compiler.QuickCodeCompiler
import com.ivyapps.composehammer.domain.quickcode.service.MaybeValid.Invalid
import com.ivyapps.composehammer.domain.quickcode.service.MaybeValid.Valid
import com.ivyapps.composehammer.persistence.QuickCodePersistence
import com.ivyapps.composehammer.sortedByOrder

@Service(Service.Level.PROJECT)
class QuickCodeService(project: Project) {
    private val persistence = project.service<QuickCodePersistence>()

    val configuration: QuickCodeConfiguration
        get() = persistence.state.configuration

    val projects: List<QCProject>
        get() = configuration.projects.sortedByOrder()

    val allGroups: List<CodeGroup>
        get() = projects.flatMap { it.groups }.sortedByOrder()

    fun hasDefinedProjects() = configuration.projects.any { it.groups.isNotEmpty() }

    fun findProjectByName(name: String): QCProject {
        return requireNotNull(projects.find { it.name == name }) {
            "Project with name '$name' doesn't exists."
        }
    }

    fun findGroupByName(name: String): CodeGroup {
        return requireNotNull(projects.firstNotNullOfOrNull { project ->
            project.groups.find { it.name == name }
        }) {
            "CodeGroup with name '$name' doesn't exists."
        }
    }

    fun findCodeItemByName(group: CodeGroup, name: String): CodeItem {
        return requireNotNull(group.codeItems.find { it.name == name }) {
            "CodeItem with name '$name' doesn't exists in ${group.codeItems}."
        }
    }

    // region Project operations
    data class ProjectInput(
        val rawName: String
    )

    inner class ProjectOperations : BaseOperations<ProjectInput, QCProject>() {
        override val items: List<QCProject>
            get() = projects

        override fun createItem(input: ProjectInput, order: Double): MaybeValid<QCProject> {
            val name = input.rawName.notBlankAndTrimmed()
                ?: return Invalid("Invalid project name: ${input.rawName}")
            return Valid(
                QCProject(
                    name = name,
                    order = order
                )
            )
        }

        override fun copyWithNewOrder(item: QCProject, newOrder: Double): QCProject {
            return item.copy(order = newOrder)
        }

        override fun updateState(updatedItems: List<QCProject>) {
            updateProjects(updatedItems)
        }
    }
    // endregion

    // region Group operations
    data class GroupInput(
        val rawName: String,
    )

    inner class GroupOperations(
        private val project: QCProject,
    ) : BaseOperations<GroupInput, CodeGroup>() {
        override val items: List<CodeGroup>
            get() = project.groups

        override fun updateState(updatedItems: List<CodeGroup>) {
            updateGroups(
                project = project,
                updatedGroups = updatedItems,
            )
        }

        override fun copyWithNewOrder(item: CodeGroup, newOrder: Double): CodeGroup {
            return item.copy(order = newOrder)
        }

        override fun createItem(input: GroupInput, order: Double): MaybeValid<CodeGroup> {
            val name = input.rawName.notBlankAndTrimmed()
                ?: return Invalid("Invalid project name: ${input.rawName}")
            return Valid(
                CodeGroup(
                    name = name,
                    order = order
                )
            )
        }
    }
    // endregion

    // region CodeItem operations
    data class CodeItemInput(
        val rawName: String,
        val rawImports: String,
        val rawCode: String,
    ) {

    }

    inner class CodeItemOperations(
        private val project: QCProject,
        private val group: CodeGroup,
    ) : BaseOperations<CodeItemInput, CodeItem>() {
        override val items: List<CodeItem>
            get() = group.codeItems

        override fun updateState(updatedItems: List<CodeItem>) {
            updateCodeItems(project, group, updatedItems)
        }

        override fun copyWithNewOrder(item: CodeItem, newOrder: Double): CodeItem {
            return item.copy(order = newOrder)
        }

        override fun createItem(
            input: CodeItemInput,
            order: Double
        ): MaybeValid<CodeItem> = with(input) {
            val name = rawName.notBlankAndTrimmed()
                ?: return@with Invalid("Invalid name: '$rawName'")
            val code = rawCode.notBlankAndTrimmed()
                ?: return@with Invalid("The code can't be blank!")

            // TODO: Support QuickCode in imports?
            val imports = rawImports.replace("import", "")
                .split("\n")
                .mapNotNull {
                    it.trim().takeIf(String::isNotBlank)
                }

            val qcCompiler = QuickCodeCompiler()
            val variables = when (val res = qcCompiler.compile(code)) {
                is QuickCodeCompiler.CompilationResult.Invalid -> return@with Invalid(
                    "Invalid code - compilation error: ${res.errMsg}"
                )

                is QuickCodeCompiler.CompilationResult.Valid -> res.variables
            }

            return Valid(
                CodeItem(
                    name = name,
                    imports = imports,
                    codeTemplate = code,
                    variables = variables,
                    order = order,
                )
            )
        }

    }
    // endregion

    private fun updateProjects(
        updated: List<QCProject>,
    ) {
        configuration.projects.clear()
        configuration.projects.addAll(updated)
    }

    private fun updateGroups(
        project: QCProject,
        updatedGroups: List<CodeGroup>
    ) {
        updateProjects(
            updated = projects.withRemoved(project) + project.copy(
                groups = updatedGroups
            )
        )
    }

    private fun updateCodeItems(
        project: QCProject,
        group: CodeGroup,
        updatedItems: List<CodeItem>
    ) {
        updateGroups(
            project = project,
            updatedGroups = project.groups.withRemoved(group) + group.copy(
                codeItems = updatedItems
            )
        )
    }
}

private fun String?.notBlankAndTrimmed(): String? = this?.trim()?.takeIf { it.isNotBlank() }