package com.pliniodev.routes

import com.pliniodev.data.model.Barbie
import com.pliniodev.service.DAOBarbieImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.barbies() {
    val service by closestDI().instance<DAOBarbieImpl>()

    get("barbies") {
        val allBarbies = service.getAllBarbies()
        call.respond(allBarbies)
    }

    get("randombarbie") {
        val allBarbies = service.getRandomBarbie()
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
        service.addBarbie(barbieRequest)
        call.respondText("Barbie created", status = HttpStatusCode.Created)
        if (barbieRequest.description.length > 1000)
            call.respondText(
                text = "description must be smaller than 1000 characters",
                status = HttpStatusCode.NotAcceptable
            )
    }

    delete("deletebarbie/{id}") {
        val barbieId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException("Barbie not found")
        service.deleteBarbie(barbieId)
        call.respond(HttpStatusCode.OK)
    }

    route("barbie/search") {
        get("/byname") {
            val name = call.request.queryParameters["name"] ?: return@get call.respondText(
                text = "Missing name",
                status = HttpStatusCode.BadRequest
            )
            val barbie = service.searchBarbieByName(name)
            call.respond(barbie)
        }

        get("/bymodel") {
            val barbieModel = call.request.queryParameters["barbieModel"] ?: return@get call.respondText(
                text = "Missing barbieModel, it is null",
                status = HttpStatusCode.BadRequest
            )
            if (barbieModel.isEmpty()){
                return@get call.respondText(
                    text = "Missing barbieModel, it is empty",
                    status = HttpStatusCode.BadRequest
                )
            }
            val barbie = service.searchBarbieByModel(barbieModel)
            call.respond(barbie)
        }
    }

    put("barbie/update/{description}") {
        val barbieId = call.request.queryParameters["id"]?.toIntOrNull() ?: throw NotFoundException("Barbie not found")
        val description = call.parameters["description"] ?: throw NotFoundException("Missing description to update")
        val isDescriptionValid = description.length < 1000 || description.isNotEmpty()
        if (isDescriptionValid) {
            service.updateDescription(barbieId, description)
            call.respond(HttpStatusCode.Accepted)
        } else {
            call.respondText(
                text = "Description must be smaller than 1000 characters and can not be empty",
                status = HttpStatusCode.NotAcceptable
            )
        }
    }
}