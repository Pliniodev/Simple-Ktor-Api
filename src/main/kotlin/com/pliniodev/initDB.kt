package com.pliniodev

import com.pliniodev.data.model.Barbies
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

fun initDB() {
    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
    LoggerFactory.getLogger(Application::class.simpleName).info("Initialized Database")

    createTables()
}

private fun createTables() = transaction {
    SchemaUtils.create(Barbies)
}