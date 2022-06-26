package com.pliniodev.service

import com.pliniodev.data.model.Barbie
import com.pliniodev.data.model.BarbieEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.transactions.transaction

class BarbieServices : BarbieService {
    override fun getAllBarbies(): Iterable<Barbie> = transaction {
        BarbieEntity.all().map(BarbieEntity::toBarbie)
    }

    override fun getRandomBarbie() = transaction{
        val allBarbies = BarbieEntity.all().map(BarbieEntity::toBarbie)
        if (allBarbies.isNotEmpty()) allBarbies.random() else null
    }

    override fun addBarbie(barbie: Barbie) = transaction  {
        BarbieEntity.new {
            this.name = barbie.name
            this.description = barbie.description.orEmpty()
            this.imageUrl = barbie.imageUrl
            this.barbieType = barbie.barbieType.orEmpty()
        }
    }

    override fun deleteBarbie(id: Int) = transaction {
        BarbieEntity[id].delete()
    }

    override fun searchBarbie(name: String) = getAllBarbies().filter { it.name.lowercase().contains(name.lowercase()) }
}