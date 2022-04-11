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
            button2.setOnClickListener {
                    if (!et6.isEnabled && !et11.isEnabled ){
                        checkMyWord("apple", row1)
                        if (myb.contains(false)){
                            row2.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    } else if (!et11.isEnabled){
                        checkMyWord("apple", row2)
                        if (myb.contains(false)){
                            row3.forEach {
                                it.isEnabled = true
                            }
                            button2.isEnabled = true
                            myb.clear()
                        }
                    } else {
                        checkMyWord("apple", row3)
                    }
            }
        }

    }

    private fun checkMyWord(word: String, myWordEt: List<EditText>) {
        val myWord = myWordEt.joinToString("") { it.text.toString() }
        var i = 0
        if (myWordEt[0].text.isNotEmpty() && myWordEt[1].text.isNotEmpty() && myWordEt[2].text.isNotEmpty() && myWordEt[3].text.isNotEmpty() && myWordEt[4].text.isNotEmpty()){
            myWordEt.forEach {
                if (word.contains(it.text.toString()) && word[i] == myWord[i]) {
                    myWordEt[i].setBackgroundColor(Color.GREEN)
                }
                if (word.contains(it.text.toString()) && word[i] != myWord[i]) myWordEt[i].setBackgroundColor(Color.BLUE)
                if (!word.contains(it.text.toString())) myWordEt[i].setBackgroundColor(Color.GRAY)
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