package com.pliniodev.routes.barbie

import com.pliniodev.data.model.Barbie
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respondText

object BarbieValidator {

    suspend fun ApplicationCall.validateAndRespond(barbiesToAdd: List<Barbie>): Boolean {
        val (message, isAcceptable) = barbiesToAdd.isAcceptable()
        val status = if (isAcceptable) HttpStatusCode.Created else HttpStatusCode.NotAcceptable
        respondText(message, status = status)
        return isAcceptable
    }

    private fun List<Barbie>.isAcceptable(): Pair<String, Boolean> {
        val hasInvalidDescription = any { it.description.length > 500 }
        val hasEmptyValue = any { it.name.isEmpty() || it.imageUrl.isEmpty() }
        return when {
            hasInvalidDescription -> "Some description is bigger than 500 characters" to false
            hasEmptyValue -> "Some barbie name or imageUrl is empty" to false
            else -> "All Barbies created" to true
        }
    }

    suspend fun ApplicationCall.validateAndRespond(barbieToAdd: Barbie): Boolean {
        val (message, isAcceptable) = barbieToAdd.isAcceptable()
        val status = if (isAcceptable) HttpStatusCode.Created else HttpStatusCode.NotAcceptable
        respondText(message, status = status)
        return isAcceptable
    }

    private fun Barbie.isAcceptable(): Pair<String, Boolean> =
        when {
            description.length > 500 -> "Description must be smaller than 500 characters" to false
            name.isEmpty() -> "Name must not be empty" to false
            imageUrl.isEmpty() -> "Image Url must not be empty" to false
            else -> "Barbie created" to true
        }

    suspend fun ApplicationCall.validateAndRespond(barbieId: Int?, description: String?): Boolean {
        val (message, isAcceptable) = isAcceptableValues(barbieId, description)
        val status = if (isAcceptable) HttpStatusCode.Accepted else HttpStatusCode.NotAcceptable
        respondText(message, status = status)
        return isAcceptable
    }

    private fun isAcceptableValues(barbieId: Int?, description: String?): Pair<String, Boolean> =
        when {
            barbieId == null -> "Missing id" to false
            description == null -> "Missing description" to false
            description.length > 500 || description.isEmpty() ->
                "Description must be smaller than 500 characters and can not be empty" to false
            else -> "Description updated" to true
        }
}