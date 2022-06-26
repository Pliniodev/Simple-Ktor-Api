package com.pliniodev.routes

import com.pliniodev.data.model.Barbie
import com.pliniodev.data.model.BarbieEntity
import com.pliniodev.service.BarbieServices
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.barbies() {
    val barbieServices by closestDI().instance<BarbieServices>()

    get("barbies") {
        val allBarbies = barbieServices.getAllBarbies()
        call.respond(allBarbies)
    }

    get("randombarbie") {
        val allBarbies = barbieServices.getRandomBarbie()
        if (allBarbies == null) {
            call.respondText(
                text = "Haven't barbies to show",
                status = HttpStatusCode.NotFound)
        } else {
            call.respond(allBarbies)
        }
    }

    post("addbarbie") {
        val barbieRequest = call.receive<Barbie>()
        barbieServices.addBarbie(barbieRequest)
        call.respondText("Barbie created", status = HttpStatusCode.Created)
        if (barbieRequest.description?.length!! > 1000)
            call.respondText(
                text = "description must be smaller than 1000 characters",
                status = HttpStatusCode.NotAcceptable
            )
    }

    delete("barbie/{id}") {
        val barbieId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException("Barbie not found")
        barbieServices.deleteBarbie(barbieId)
        call.respond(HttpStatusCode.OK)
    }

    get("barbie/{name}") {
        val name = call.parameters["name"] ?: return@get call.respondText(
            text = "Missing barbie name",
            status = HttpStatusCode.BadRequest
        )
        val barbie = barbieServices.searchBarbie(name)
        call.respond(barbie)
    }
}