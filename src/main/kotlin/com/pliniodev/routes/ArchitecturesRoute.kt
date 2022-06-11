package com.pliniodev.routes

import com.pliniodev.data.model.Architecture
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//to use the API locally with android emulator
private const val yourIP = "192.168.100.16"

private const val BASE_URL = "http://$yourIP:8100"

//to access static files at architectures package
private val urls = buildList {
    for (i in 1..10) {
        add("$BASE_URL/architectures/arch$i.jpg")
    }
}

private val architectures = buildList {
    urls.forEachIndexed { i, url ->
        add(Architecture("Arch $i", "this is arch $i architecture image", url))
    }
}

fun Route.randomArchitecture() {
    get("/randomarchitecture") {
        call.respond(
            HttpStatusCode.OK,
            architectures.random()
        )
    }
}
