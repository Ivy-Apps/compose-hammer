package com.ivyapps.composehammer.toolwindow.component

import com.intellij.icons.AllIcons
import com.intellij.ui.dsl.builder.Panel

fun Panel.reviewAndTelegramPrompt(
    indent: Boolean
) {
    group(indent = indent) {
        row {
            icon(AllIcons.General.Balloon)
            label("Like the plugin?").bold()
        }
        row {
            text("Help us make it better!")
        }
        row {
            label("Join our invite-only")
            browserLink(
                "Telegram chat",
                "https://t.me/+U9Qn68cZxYIxNDA0"
            ).bold()
        }
        row {
            label("Give us feedback")
            browserLink(
                "Review Compose Hammer",
                "https://plugins.jetbrains.com/plugin/21912-compose-hammer/reviews"
            ).bold()
        }
        row {
            text(
                """
                    <i><b>P.S.</b> These actions motivate us.<br>
                    More importantly they give us the feedback<br>
                    that we need to grow and improve the plugin.<br>
                    => you may get better features in the process.</i>
                """.trimIndent()
            )
        }
    }
}