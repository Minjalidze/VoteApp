package com.minjalidze.anonimousvotes.data.api

import com.minjalidze.anonimousvotes.data.models.VoteModel
import kotlinx.serialization.json.Json
import java.net.URL
import java.nio.charset.Charset
import java.util.concurrent.Executors

class API {
    companion object {
        private var votes: ArrayList<VoteModel> = ArrayList()
        private val executor = Executors.newSingleThreadExecutor()

        @JvmStatic
        fun initialize() {
            executor.execute {
                var str = URL("https://vote-mobile-api.whywelive.me/vote").readText(Charset.forName("UTF-8"))
                votes = Json.decodeFromString(str)
            }
        }

        @JvmStatic
        fun getVotes() : ArrayList<VoteModel> {
            return votes
        }
    }
}