package com.minjalidze.anonimousvotes.data.api

import com.minjalidze.anonimousvotes.data.models.vote.VoteModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URL
import java.nio.charset.Charset

class API {
    companion object {
        private lateinit var votes: ArrayList<VoteModel>

        @JvmStatic
        suspend fun initialize() = coroutineScope {
            launch {
                var str = URL("https://vote-mobile-api.whywelive.me/vote").readText(Charset.forName("UTF-8"))
                votes = Json.decodeFromString(str)
                delay(1000L)
            }
        }

        @JvmStatic
        fun getVotes() : ArrayList<VoteModel> {
            return votes
        }
        @JvmStatic
        fun selectVote(id: Int) : VoteModel? {
            var neededVote: VoteModel? = null
            votes.forEach {
                if (it.id == id) {
                    neededVote = it
                    return@forEach
                }
            }
            return neededVote
        }
    }
}