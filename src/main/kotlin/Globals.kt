package dev.xaaf

import io.github.cdimascio.dotenv.Dotenv
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.Color

/**
 * A utility object which holds a bunch of values that are used multiple
 * times throughout the entire codebase.
 */
object Globals {
    const val BOT_VERSION: String = "0.1.0"
    const val JDA_VERSION: String = "5.0.0-beta.24"

    val logger: Logger = LoggerFactory.getLogger("Warden")
    val dotenv: Dotenv = Dotenv.load()

    val RED: Color = Color(165, 77, 71)
    val GREEN: Color = Color(27, 175, 110)
    val BLUE: Color = Color(71, 113, 166)
    val GREY: Color = Color(120, 133, 150)
}