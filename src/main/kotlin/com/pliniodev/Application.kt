package com.pliniodev

import io.ktor.server.application.Application
import com.pliniodev.plugins.configureMonitoring
import com.pliniodev.plugins.configureSerialization
import com.pliniodev.plugins.configureStatusPages
import com.pliniodev.routes.apiRoute
import io.ktor.server.routing.routing
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
