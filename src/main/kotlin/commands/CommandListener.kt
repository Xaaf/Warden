package dev.xaaf.commands

import dev.xaaf.Globals
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction

/**
 * Implementation of the `ListenerAdapter` from JDA. It also contains some
 * utility methods for registering slash commands to the bot.
 *
 * @see ListenerAdapter
 */
object CommandListener : ListenerAdapter() {
    private val commands = mutableMapOf<String, ICommand>()

    /**
     * Register a command to the bot. It will then be usable as a slash
     * command like any other.
     *
     * @param command Reference to the new command.
     * @see ICommand
     */
    private fun registerCommand(command: ICommand) {
        if (commands.containsKey(command.name)) {
            Globals.logger.warn("Attempting to register '${command.name}' twice!")
        }

        commands[command.name] = command
    }

    /**
     * Register multiple commands to the bot. They will then be usable as
     * slash commands like any other.
     *
     * @param commands References to the new commands.
     * @see ICommand
     */
    fun registerCommands(vararg commands: ICommand) {
        for (command in commands) {
            registerCommand(command)
        }
    }

    /**
     * Push registered commands to the API. This registers them on the
     * Discord-side, after which they'll show up in servers in the
     * slash command menu.
     *
     * @param jdaCommands Reference to `CommandListUpdateAction` from the
     *                      JDA API.
     * @see CommandListUpdateAction
     */
    fun addCommands(jdaCommands: CommandListUpdateAction) {
        Globals.logger.info("Registering commands to JDA")

        commands.forEach { entry ->
            Globals.logger.info("Added '/${entry.key}'")

            jdaCommands.addCommands(
                entry.value.getCommandData()
            )
        }

        jdaCommands.queue()
    }

    /**
     * Interaction handler that is executed whenever a slash command is sent.
     *
     * @param event Specific slash command event that was sent out.
     * @see SlashCommandInteractionEvent
     */
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        // Warden only functions in guilds, so return when called from DMs
        if (event.guild == null) return

        // Ignore commands we don't know
        if (!commands.containsKey(event.name)) {
            event.reply("Unknown command!").setEphemeral(true).queue()
            return
        }

        commands[event.name]?.execute(event)
    }
}