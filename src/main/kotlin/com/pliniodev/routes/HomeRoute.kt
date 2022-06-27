package com.pliniodev.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import kotlinx.html.*

fun Route.homeRoute() {
    get("/") {
        val name = "Ktor"
        call.respondHtml(HttpStatusCode.OK) {
            head {
                title {
                    +name
                }
            }
            body {
                h1 {
                    +"Barbie api!"
                }
                h4 {
                    p {
                        +"Hello this is the barbie api home :)"
                    }
                    a("https://kotlinlang.org") {
                        +"please see the documentation here"
                    }
                }
            }
        }
    }
}