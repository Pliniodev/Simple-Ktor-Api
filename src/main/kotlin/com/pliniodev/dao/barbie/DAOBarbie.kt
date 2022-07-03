package com.pliniodev.dao.barbie

import com.pliniodev.data.model.Barbie
import com.pliniodev.data.model.BarbieEntity

interface DAOBarbie {

    fun getAllBarbies() : Iterable<Barbie>

    fun getRandomBarbie() : Barbie?

    fun addBarbie(barbie: Barbie) : BarbieEntity

    fun addAllBarbies(barbies: List<Barbie>) : List<BarbieEntity>

    fun deleteBarbie(id: Int)

    fun searchBarbieByName(name: String): List<Barbie>

    fun searchBarbieByModel(barbieModel: String): List<Barbie>

    fun updateDescription(id: Int, description: String)
}