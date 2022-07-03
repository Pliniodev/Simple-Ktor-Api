package com.pliniodev

import com.pliniodev.data.model.BarbieTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

object DbFactory {

    /**
     * Initialize PostgreSQL database
     */
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
        SchemaUtils.create(BarbieTable)
    }

//    /**
//     * Initialize H2 database to local tests
//     */
//    fun initH2Db() {
//        Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
//        LoggerFactory.getLogger(Application::class.simpleName).info("Initialized Database")
//
//        createTables()
//    }
//
//    private fun createTables() = transaction {
//        SchemaUtils.create(BarbieTable)
//    }
}