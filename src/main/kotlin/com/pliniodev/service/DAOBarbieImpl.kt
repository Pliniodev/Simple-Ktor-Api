package com.pliniodev.service

import com.pliniodev.data.model.Barbie
import com.pliniodev.data.model.BarbieEntity
import org.jetbrains.exposed.sql.transactions.transaction

class DAOBarbieImpl : DAOBarbie {

    override fun getAllBarbies(): Iterable<Barbie> = transaction {
        BarbieEntity.all().map(BarbieEntity::toBarbie)
    }

    override fun getRandomBarbie() = transaction {
        val allBarbies = BarbieEntity.all().map(BarbieEntity::toBarbie)
        if (allBarbies.isNotEmpty()) allBarbies.random() else null
    }

    override fun addBarbie(barbie: Barbie) = transaction {
        BarbieEntity.new {
            this.name = barbie.name
            this.description = barbie.description
            this.imageUrl = barbie.imageUrl
            this.barbieModel = barbie.barbieModel
        }
    }

    override fun addAllBarbies(barbies: List<Barbie>) = transaction {
        barbies.map { barbie ->
            BarbieEntity.new {
                this.name = barbie.name
                this.description = barbie.description
                this.imageUrl = barbie.imageUrl
                this.barbieModel = barbie.barbieModel
            }
        }
    }

    override fun deleteBarbie(id: Int) = transaction {
        BarbieEntity[id].delete()
    }

    override fun searchBarbieByName(name: String) =
        getAllBarbies().filter { it.name.lowercase().contains(name.lowercase()) }

    override fun searchBarbieByModel(barbieModel: String) =
        getAllBarbies().filter { it.barbieModel.lowercase().contentEquals(barbieModel.lowercase()) }

    override fun updateDescription(id: Int, description: String) = transaction {
        BarbieEntity[id].description = description
    }
}