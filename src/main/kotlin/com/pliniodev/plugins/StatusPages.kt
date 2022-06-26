package com.pliniodev.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException

fun Application.configureStatusPages(){
    install(StatusPages) {
        exception { call, entityNotFoundException: EntityNotFoundException ->
            call.respond(entityNotFoundException)
        }
    }
}