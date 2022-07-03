package com.pliniodev.data.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Barbies: IntIdTable() {
    val name = varchar("name", 255)
    val description = varchar("description", 500)
    val imageUrl = varchar("imageUrl", 255)
    val barbieModel = varchar("barbieModel", 255)
}

class BarbieEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<BarbieEntity>(Barbies)

    var name by Barbies.name
    var description by Barbies.description
    var imageUrl by Barbies.imageUrl
    var barbieModel by Barbies.barbieModel

    override fun toString(): String = "Barbie($name, $description, $imageUrl, $barbieModel)"

    fun toBarbie() = Barbie(id.value, name, description, imageUrl, barbieModel)
}

@Serializable
data class Barbie(
    val id: Int? = null,
    val name: String,
    var description: String = "",
    val imageUrl: String,
    val barbieModel: String = "generic",
)

