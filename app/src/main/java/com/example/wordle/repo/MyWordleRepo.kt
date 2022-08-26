package com.example.wordle.repo

import com.example.wordle.data.NewWord
import com.example.wordle.data.WordleData
import com.example.wordle.network.MyWordleApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyWordleRepo @Inject constructor(private val myWordleApi: MyWordleApi) {

    suspend fun getWordleData(): List<WordleData> = withContext(Dispatchers.IO){
        myWordleApi.getWordleData()
    }
    suspend fun addWord(word : NewWord) = withContext(Dispatchers.IO){
        myWordleApi.addWord(word)
    }
}