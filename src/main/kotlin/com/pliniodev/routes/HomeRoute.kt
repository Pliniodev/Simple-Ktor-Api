package com.pliniodev.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import kotlinx.html.head
import kotlinx.html.title
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.h4
import kotlinx.html.p
import kotlinx.html.a

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
                    a("https://app.getpostman.com/run-collection/19092765-40e6fc2b-f3ab-4178-9c0c-748e61a627a9?action=collection%2Ffork&collection-url=entityId%3D19092765-40e6fc2b-f3ab-4178-9c0c-748e61a627a9%26entityType%3Dcollection%26workspaceId%3D360b7237-0065-4ccb-9f5d-390c64cb9b04") {
                        +"Fork the postman collection here"
                    }
                }
            }
        }
    }
}