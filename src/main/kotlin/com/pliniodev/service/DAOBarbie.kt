package com.pliniodev.service

import com.pliniodev.data.model.Barbie
import com.pliniodev.data.model.BarbieEntity

interface DAOBarbie {

    fun getAllBarbies() : Iterable<Barbie>

    fun getRandomBarbie() : Barbie?

    fun addBarbie(barbie: Barbie) : BarbieEntity

    fun deleteBarbie(id: Int)

    fun searchBarbieByName(name: String): List<Barbie>

    fun searchBarbieByModel(type: String): List<Barbie>

    fun updateDescription(id: Int, description: String)
}