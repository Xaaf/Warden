package dev.xaaf

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import java.awt.Color

/**
 * Utility class to simplify the creation and use of embeds. Internally, it holds
 * an `EmbedBuilder`, which is being modified whenever the appropriate methods
 * are called.
 *
 * @see EmbedBuilder
 */
class Embed(private val title: String, private val description: String, private val color: Color) {
    constructor(title: String): this(title, "", Globals.GREY)
    constructor(title: String, description: String): this(title, description, Globals.GREY)
    constructor(title: String, color: Color): this(title, "", color)

    private val embed: EmbedBuilder = EmbedBuilder().setTitle(title).setDescription(description).setColor(color)

    /**
     * Change the title of the `Embed`.
     *
     * @param newTitle The new title.
     */
    fun setTitle(newTitle: String) {
        embed.setTitle(newTitle)
    }

    /**
     * Change the description of the `Embed`.
     *
     * @param newDescription The new description.
     */
    fun setDescription(newDescription: String) {
        embed.setDescription(newDescription)
    }

    /**
     * Change the color of the `Embed`.
     *
     * @param newColor The new color.
     */
    fun setColor(newColor: Color) {
        embed.setColor(newColor)
    }

    /**
     * @return The embed, built as a MessageEmbed.
     * @see MessageEmbed
     */
    fun get(): MessageEmbed {
        return embed.build()
    }
}