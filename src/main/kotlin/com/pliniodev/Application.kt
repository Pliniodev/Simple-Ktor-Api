package com.pliniodev

import io.ktor.server.application.*
import com.pliniodev.plugins.*
import com.pliniodev.routes.apiRoute
import io.ktor.http.*
import io.ktor.server.application.call
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.kodein.di.ktor.di

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DbFactory.init()

    configureSerialization()
    configureMonitoring()
    configureStatusPages()

    di { bindServices() }
    routing { apiRoute() }
}
