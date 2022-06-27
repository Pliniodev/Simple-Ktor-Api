package com.pliniodev.routes

import io.ktor.server.routing.*

fun Routing.apiRoute() {
    route("/") {
        homeRoute()
    }
    route("/api/v1") {
        barbies()
    }
}
