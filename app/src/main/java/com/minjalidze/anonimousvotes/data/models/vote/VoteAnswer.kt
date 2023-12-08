package com.minjalidze.anonimousvotes.data.models.vote

import kotlinx.serialization.Serializable

@Serializable
data class VoteAnswer (val id: Int, val title: String)
