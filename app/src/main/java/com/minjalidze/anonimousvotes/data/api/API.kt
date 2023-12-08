package com.minjalidze.anonimousvotes.data.api

import com.minjalidze.anonimousvotes.data.models.vote.VoteModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URL
import java.nio.charset.Charset

class API {
    companion object {
        private var votes: ArrayList<VoteModel> = ArrayList()

        @JvmStatic
        suspend fun initialize() = coroutineScope {
            launch {
                val str = URL("https://vote-mobile-api.whywelive.me/vote").readText(Charset.forName("UTF-8"))
                votes = Json.decodeFromString(str)
            }
        }

        @JvmStatic
        fun getVotes() : ArrayList<VoteModel> {
            return votes
        }
        @JvmStatic
        fun selectVote(id: Int) : VoteModel? {
            return votes.find { it.id == id }
        }
    }
}