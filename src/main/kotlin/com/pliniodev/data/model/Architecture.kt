package com.pliniodev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Architecture(
    val name: String,
    val description: String,
    val imageUrl: String
)