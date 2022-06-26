package com.pliniodev

import com.pliniodev.service.DAOBarbieImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices() {
    bind<DAOBarbieImpl>() with singleton { DAOBarbieImpl() }
}