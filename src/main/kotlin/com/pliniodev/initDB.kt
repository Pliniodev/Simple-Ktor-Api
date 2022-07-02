package com.pliniodev

import com.pliniodev.data.model.Barbies
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

object DbFactory {
    fun init() {
        val pool = hikari()
        val database = Database.connect(pool)

        createTables(database)
        LoggerFactory.getLogger(Application::class.simpleName).info("Initialized Database")
    }

    private fun hikari(): HikariDataSource {
        val hikariConfig = HikariConfig("hikari.properties")
        return HikariDataSource(hikariConfig)
    }

    private fun createTables(database: Database) = transaction(database) {
        SchemaUtils.create(Barbies)
    }
}