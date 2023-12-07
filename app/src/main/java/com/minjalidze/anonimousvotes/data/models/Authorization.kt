package com.minjalidze.anonimousvotes.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Authorization(val token: String)
