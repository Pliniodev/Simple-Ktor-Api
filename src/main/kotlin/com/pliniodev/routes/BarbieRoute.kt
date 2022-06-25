package com.pliniodev.routes

import com.pliniodev.data.model.Barbie
import com.pliniodev.service.BarbieServices
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.ktor.di

fun Route.barbies() {
    val barbieServices by closestDI().instance<BarbieServices>()

    get("barbies") {
        val allBarbies = barbieServices.getAllBarbies()
        call.respond(allBarbies)
    }

    get("randombarbie") {
        val allBarbies = barbieServices.getAllBarbies()
        call.respond(allBarbies)
    }

    post("addbarbie") {
        val barbieRequest = call.receive<Barbie>()
        barbieServices.addBarbie(barbieRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("barbie/{id}") {
        val barbieId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException("Barbie not found")
        barbieServices.deleteBarbie(barbieId)
        call.respond(HttpStatusCode.OK)
    }
}