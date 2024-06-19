package dev.xaaf.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

/**
 * Generalised interface for the creation of new commands.
 * @see StatusCommand
 */
interface ICommand {
    val name: String
    val description: String

    /**
     * Retrieve the `SlashCommandData` object for this command.
     *
     * @see SlashCommandData
     */
    fun getCommandData(): SlashCommandData {
        return Commands.slash(name, description)
    }

    /**
     * Execute the body of this command.
     *
     * @see SlashCommandData
     */
    fun execute(event: SlashCommandInteractionEvent) {
        event.deferReply(true).queue()
        event.hook.sendMessage("This command has not been implemented yet!").queue()
    }
}