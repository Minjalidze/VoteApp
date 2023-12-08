package com.minjalidze.anonimousvotes.data.models.vote

import kotlinx.serialization.Serializable

@Serializable
data class VoteModel (val id: Int, val title: String, val items: ArrayList<VoteAnswer>, val voted: Int = 0)