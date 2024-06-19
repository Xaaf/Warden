package dev.xaaf

import dev.xaaf.commands.CommandListener
import dev.xaaf.commands.StatusCommand
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import java.sql.ResultSet
import kotlin.system.exitProcess

fun main() {
    Globals.logger.info("Starting up Warden...")

    Globals.logger.info("Performing database connection test...")
    val result: ResultSet? = DatabaseHandler.query("SELECT sqlite_version()")

    if (result == null) {
        Globals.logger.error("Database connection failed! Exiting...")
        exitProcess(-1)
    }

    Globals.logger.info("Database connection succeeded! (SQLite v${result.getString(1)})")

    val api: JDA = JDABuilder.createDefault(Globals.dotenv.get("BOT_TOKEN"))
        .enableIntents(
            GatewayIntent.MESSAGE_CONTENT,
            GatewayIntent.GUILD_MEMBERS,
        ).build()

    Globals.logger.info("Setting Warden's presence")
    api.presence.setPresence(OnlineStatus.ONLINE, Activity.watching("over the inmates."))

    CommandListener.registerCommands(
        StatusCommand()
    )

    CommandListener.addCommands(api.updateCommands())

    api.addEventListener(CommandListener)
}