package com.pliniodev.routes

import com.pliniodev.data.model.Barbie

object BarbieValidator {

    fun List<Barbie>.isAcceptable(): Pair<String, Boolean> {
        val hasInvalidDescription = any { it.description.length > 500 }
        val hasEmptyValue = any { it.name.isEmpty() || it.imageUrl.isEmpty() }
        return when {
            hasInvalidDescription -> "Some description is bigger than 500 characters" to false
            hasEmptyValue -> "Some barbie name or imageUrl is empty" to false
            else -> "All Barbies created" to true
        }
    }

    fun Barbie.isAcceptable(): Pair<String, Boolean> =
        if (description.length > 500) "description must be smaller than 500 characters" to false
        else "Barbie created" to true
}