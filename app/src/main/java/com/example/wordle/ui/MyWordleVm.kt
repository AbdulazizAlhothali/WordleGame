package com.example.wordle.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordle.data.NewWord
import com.example.wordle.data.WordleData
import com.example.wordle.repo.MyWordleRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyWordleVm @Inject constructor(private val wordleRepo: MyWordleRepo) : ViewModel() {

    fun getWordleData(): LiveData<List<WordleData>> {
        val myWords = MutableLiveData<List<WordleData>>()
        viewModelScope.launch {
            try {
                myWords.postValue(wordleRepo.getWordleData())
            } catch (e: Throwable) {
                Log.e("Wordle", "Wordle Problem : ${e.localizedMessage}")
            }
        }
        return myWords
    }

    fun addWord(word: NewWord){
        viewModelScope.launch {
            wordleRepo.addWord(word)
        }
    }
}