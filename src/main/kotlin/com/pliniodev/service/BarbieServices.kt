package com.pliniodev.service

import com.pliniodev.data.model.Barbie
import com.pliniodev.data.model.BarbieEntity
import io.ktor.server.plugins.*
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

}