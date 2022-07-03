package com.pliniodev.plugins

import io.ktor.server.plugins.callloging.CallLogging
import org.slf4j.event.Level
import io.ktor.server.request.path
import io.ktor.server.application.Application
import io.ktor.server.application.install

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}
