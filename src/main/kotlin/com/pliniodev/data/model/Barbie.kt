package com.pliniodev.data.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Barbies: IntIdTable() {
    val name = varchar("name", 255)
    val description = varchar("description", 255)
    val imageUrl = varchar("imageUrl", 255)
}

class BarbieEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<BarbieEntity>(Barbies)

    var name by Barbies.name
    var description by Barbies.description
    var imageUrl by Barbies.imageUrl

    override fun toString(): String = "Barbie($name, $description, $imageUrl)"

    fun toBarbie() = Barbie(id.value, name, description, imageUrl)
}

@Serializable
data class Barbie(
    val id: Int,
    val name: String,
    val description: String? = null,
    val imageUrl: String,
)

