package com.pliniodev.service

import com.pliniodev.data.model.Barbie
import com.pliniodev.data.model.BarbieEntity

interface BarbieService {

    fun getAllBarbies() : Iterable<Barbie>

    fun getRandomBarbie() : Barbie?

    fun addBarbie(barbie: Barbie) : BarbieEntity

    fun deleteBarbie(id: Int)
}