package com.example.wordle.network

import com.example.wordle.data.NewWord
import com.example.wordle.data.WordleData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyWordleApi {

    @GET("names")
    suspend fun getWordleData(): List<WordleData>

    @POST("names")
    suspend fun addWord(@Body word : NewWord)

}