package com.pliniodev

import io.ktor.server.application.*
import com.pliniodev.plugins.*
import com.pliniodev.routes.apiRoute
import io.ktor.server.routing.*
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
