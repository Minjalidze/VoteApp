package com.minjalidze.anonimousvotes.data.models

import kotlinx.serialization.Serializable

@Serializable
data class VoteAnswer (val id: Int, val title: String)
