package dev.xaaf

import java.sql.*

/**
 * An object to make interacting with the database for the bot easier. It
 * sets up a connection and keeps it open, with utility methods for
 * performing queries on the database.
 */
object DatabaseHandler {
    private var DB_URL: String = Globals.dotenv.get("DB_URL")
    private var DB_USER: String = Globals.dotenv.get("DB_USER")
    private var DB_PASS: String = Globals.dotenv.get("DB_PASS")

    private lateinit var connection: Connection

    /**
     * The DatabaseHandler has an established connection and is ready to send
     * queries and receive data from the database.
     */
    var ready: Boolean = false
        private set

    init {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:$DB_URL", DB_USER, DB_PASS)
            ready = true
        } catch (e: SQLException) {
            Globals.logger.error(e.message)
            ready = false
        }
    }

    /**
     * Send a query to the database.
     *
     * @param toQuery Query to execute on the database.
     * @return `ResultSet` gathered from the query.
     * @see ResultSet
     */
    fun query(toQuery: String): ResultSet? {
        Globals.logger.info("Querying the database with '$toQuery'")
        val statement: PreparedStatement = connection.prepareStatement(toQuery)

        try {
            return statement.executeQuery()
        } catch (e: SQLException) {
            Globals.logger.error(e.message)

            return null
        }
    }
}