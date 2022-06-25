package com.pliniodev

import com.pliniodev.service.BarbieServices
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices() {
    bind<BarbieServices>() with singleton { BarbieServices() }
}