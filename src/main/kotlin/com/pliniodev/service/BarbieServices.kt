package com.pliniodev.service

import com.pliniodev.data.model.Barbie
import com.pliniodev.data.model.BarbieEntity
import org.jetbrains.exposed.sql.transactions.transaction

class BarbieServices : BarbieService {
    override fun getAllBarbies(): Iterable<Barbie> = transaction {
        BarbieEntity.all().map(BarbieEntity::toBarbie)
    }

    override fun getRandomBarbie() = transaction{
        BarbieEntity.all().map(BarbieEntity::toBarbie).random()
    }

    override fun addBarbie(barbie: Barbie) = transaction  {
        BarbieEntity.new {
            this.name = barbie.name
            this.description = barbie.description.orEmpty()
            this.imageUrl = barbie.imageUrl
        }
    }

    override fun deleteBarbie(id: Int) = transaction {
        BarbieEntity[id].delete()
    }

}