package dev.xaaf.commands

import dev.xaaf.Embed
import dev.xaaf.Globals
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

/**
 * The /status command will retrieve some information about the bot and
 * return this to the user in an embed. The information it gathers is
 * the version of the bot, JDA, the server ping and the gateway ping.
 */
class StatusCommand(
    override val name: String = "status",
    override val description: String = "Check the bot's status."
) : ICommand {
    override fun execute(event: SlashCommandInteractionEvent) {
        event.deferReply(true).queue()

        val embed: Embed = Embed(
            "Status",
            """
            **Server Ping** Calculating...
            **Gateway Ping** Calculating..
            
            **Bot Version** ${Globals.BOT_VERSION}
            **JDA Version** ${Globals.JDA_VERSION}
        """.trimIndent(), Globals.GREY
        )

        val time: Long = System.currentTimeMillis()
        event.hook.sendMessageEmbeds(embed.get()).queue() {
            embed.setDescription(
                """
            **Server Ping** ${System.currentTimeMillis() - time}ms
            **Gateway Ping** ${event.jda.gatewayPing}ms
            
            **Bot Version** ${Globals.BOT_VERSION}
            **JDA Version** ${Globals.JDA_VERSION}
            """.trimIndent()
            )
            embed.setColor(Globals.GREEN)

            event.hook.editOriginalEmbeds(embed.get()).queue()
        }
    }
}