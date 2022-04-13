package com.example.wordle

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.wordle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myb = mutableListOf<Boolean>()
    private val myColors = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.apply {
            val row1 = listOf(et1,et2,et3,et4,et5)
            val row2 = listOf(et6,et7,et8,et9,et10)
            val row3 = listOf(et11,et12,et13,et14,et15)
            val row4 = listOf(et16,et17,et18,et19,et20)
            val row5 = listOf(et21,et22,et23,et24,et25)
            val row6 = listOf(et26,et27,et28,et29,et30)
            button2.setOnClickListener {
                when {
                    !et6.isEnabled && !et11.isEnabled && !et16.isEnabled && !et21.isEnabled && !et26.isEnabled -> {
                        checkMyWord("تفاحة", row1)
                        if (myb.contains(false)){
                            row2.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    !et11.isEnabled && !et16.isEnabled && !et21.isEnabled && !et26.isEnabled -> {
                        checkMyWord("تفاحة", row2)
                        if (myb.contains(false)){
                            row3.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    !et16.isEnabled && !et21.isEnabled && !et26.isEnabled -> {
                        checkMyWord("تفاحة", row3)
                        if (myb.contains(false)){
                            row4.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    !et21.isEnabled && !et26.isEnabled -> {
                        checkMyWord("تفاحة", row4)
                        if (myb.contains(false)){
                            row5.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    !et26.isEnabled -> {
                        checkMyWord("تفاحة", row5)
                        if (myb.contains(false)){
                            row6.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    else -> {
                        checkMyWord("تفاحة", row6)
                        if (button2.isEnabled){
                            binding.button2.isEnabled = false
                            Toast.makeText(this@MainActivity,"try again",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }

    private fun checkMyWord(word: String, myWordEt: List<EditText>) {
        val myWord = myWordEt.joinToString("") { it.text.toString() }
        var i = 0
        if (myWord.isNotEmpty()){
            myWordEt.forEach {
                if (word.contains(it.text.toString()) && word[i] == myWord[i]) it.setBackgroundColor(Color.GREEN)
                if (word.contains(it.text.toString()) && word[i] != myWord[i]) it.setBackgroundColor(Color.BLUE)
                if (!word.contains(it.text.toString())) it.setBackgroundColor(Color.GRAY)
                i++
                it.isEnabled = false
                val c = (it.background as ColorDrawable).color
                myb += c == Color.GREEN
                myColors += c
            }
            if (myColors.contains(Color.BLUE) || myColors.contains(Color.GRAY)){
                binding.button2.isEnabled = true
                myColors.clear()
            } else {
                binding.button2.isEnabled = false
                Toast.makeText(this,"مبرووووكك",Toast.LENGTH_SHORT).show()
            }
        } else {
            myWordEt.forEach {
                if (it.text.isNullOrEmpty()) it.error = "must be filled"
            }
        }
    }
}