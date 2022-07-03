package com.pliniodev.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException

fun Application.configureStatusPages(){
    install(StatusPages) {
        exception { call, entityNotFoundException: EntityNotFoundException ->
            call.respond(entityNotFoundException)
        }
    }
}