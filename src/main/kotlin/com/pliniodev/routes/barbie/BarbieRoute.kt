package com.pliniodev.routes.barbie

import com.pliniodev.data.model.Barbie
import com.pliniodev.routes.barbie.BarbieValidator.validateAndRespond
import com.pliniodev.dao.barbie.DAOBarbieImpl
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
        call.respond(service.getAllBarbies())
    }

    get("randombarbie") {
        val allBarbies = service.getRandomBarbie()
        if (allBarbies == null) {
            call.respondText(
                text = "Haven't barbies to show",
                status = HttpStatusCode.NotFound
            )
        } else call.respond(allBarbies)
    }

    post("addbarbie") {
        val barbieToAdd = call.receive<Barbie>()
        val isAcceptable = call.validateAndRespond(barbieToAdd)
        if (isAcceptable) service.addBarbie(barbieToAdd)
    }

    post("addbarbies") {
        val barbiesToAdd = call.receive<List<Barbie>>()
        val isAcceptable = call.validateAndRespond(barbiesToAdd)
        if (isAcceptable) service.addAllBarbies(barbiesToAdd)
    }

    delete("deletebarbie/{id}") {
        val barbieId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException("Barbie not found")
        service.deleteBarbie(barbieId)
        call.respondText("Barbie with id $barbieId deleted", status = HttpStatusCode.OK)
    }

    route("barbie/search") {
        get("/byname") {
            val name = call.request.queryParameters["name"] ?: return@get call.respondText(
                text = "Missing name",
                status = HttpStatusCode.BadRequest
            )
            call.respond(service.searchBarbieByName(name))
        }

        get("/bymodel") {
            val barbieModel = call.request.queryParameters["barbieModel"] ?: return@get call.respondText(
                text = "Missing barbieModel, it is null",
                status = HttpStatusCode.BadRequest
            )
            if (barbieModel.isEmpty()) {
                return@get call.respondText(
                    text = "Missing barbieModel, it is empty",
                    status = HttpStatusCode.BadRequest
                )
            }
            call.respond(service.searchBarbieByModel(barbieModel))
        }
    }

    put("barbie/update/{description}") {
        val barbieId = call.request.queryParameters["id"]?.toIntOrNull()
        val description = call.parameters["description"]
        val isAcceptable = call.validateAndRespond(barbieId, description)
        if (isAcceptable && barbieId != null && description != null) {
            service.updateDescription(barbieId, description)
        }
    }
}