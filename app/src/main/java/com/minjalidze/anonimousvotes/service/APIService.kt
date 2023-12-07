package com.minjalidze.anonimousvotes.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST

interface APIService {
    @POST("/auth")
    suspend fun register(): Response<ResponseBody>
}