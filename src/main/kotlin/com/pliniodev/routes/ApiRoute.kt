package com.pliniodev.routes

import com.pliniodev.routes.barbie.barbies
import io.ktor.server.routing.route
import io.ktor.server.routing.Routing

fun Routing.apiRoute() {
    route("/") {
        homeRoute()
    }
    route("/api/v1") {
        barbies()
    }
}
