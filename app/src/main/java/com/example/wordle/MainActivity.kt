package com.example.wordle

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.wordle.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myb = mutableListOf<Boolean>()
    private val myColors = mutableListOf<Int>()
    val words = listOf("فاطمة", "مشاري", "سلطان").random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            val row1 = listOf(et1, et2, et3, et4, et5)
            val row2 = listOf(et6, et7, et8, et9, et10)
            val row3 = listOf(et11, et12, et13, et14, et15)
            val row4 = listOf(et16, et17, et18, et19, et20)
            val row5 = listOf(et21, et22, et23, et24, et25)
            val row6 = listOf(et26, et27, et28, et29, et30)
            button2.setOnClickListener {
                when {
                    !et6.isEnabled && !et11.isEnabled && !et16.isEnabled && !et21.isEnabled && !et26.isEnabled -> {
                        checkMyWord(words, row1)
                        if (myb.contains(false)) {
                            row2.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    !et11.isEnabled && !et16.isEnabled && !et21.isEnabled && !et26.isEnabled -> {
                        checkMyWord(words, row2)
                        if (myb.contains(false)) {
                            row3.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    !et16.isEnabled && !et21.isEnabled && !et26.isEnabled -> {
                        checkMyWord(words, row3)
                        if (myb.contains(false)) {
                            row4.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    !et21.isEnabled && !et26.isEnabled -> {
                        checkMyWord(words, row4)
                        if (myb.contains(false)) {
                            row5.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    !et26.isEnabled -> {
                        checkMyWord(words, row5)
                        if (myb.contains(false)) {
                            row6.forEach {
                                it.isEnabled = true
                            }
                            myb.clear()
                        }
                    }
                    else -> {
                        checkMyWord(words, row6)
                        if (button2.isEnabled) {
                            binding.button2.isEnabled = false
                            val alertDialog = AlertDialog.Builder(this@MainActivity)
                            alertDialog.apply {
                                setMessage("لقد خسرت الكلمة كانت $words")
                                setNeutralButton("حاول مرة اخرى!") { _, _ ->
                                    listOf(row1, row2, row3, row4, row5, row6).forEach { editText ->
                                        editText.forEach {
                                            it.text = null
                                        }
                                    }
                                    recreate()
                                }
                            }.create().show()
                        }
                    }
                }
            }
        }

    }

    private fun checkMyWord(word: String, myWordEt: List<EditText>) {
        val myWord = myWordEt.joinToString("") { it.text.toString() }
        var i = 0
        if (myWord.isNotEmpty()) {
            myWordEt.forEach {
                if (word.contains(it.text.toString()) && word[i] == myWord[i]) it.setBackgroundColor(
                    Color.GREEN
                )
                if (word.contains(it.text.toString()) && word[i] != myWord[i]) it.setBackgroundColor(
                    Color.BLUE
                )
                if (!word.contains(it.text.toString())) it.setBackgroundColor(Color.GRAY)
                i++
                it.isEnabled = false
                val c = (it.background as ColorDrawable).color
                myb += c == Color.GREEN
                myColors += c
            }
            if (myColors.contains(Color.BLUE) || myColors.contains(Color.GRAY)) {
                binding.button2.isEnabled = true
                myColors.clear()
            } else {
                binding.button2.isEnabled = false
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("myWord", words)
                lifecycleScope.launch(Dispatchers.Main) {
                    delay(2000)
                    startActivity(intent)
                }

            }
        } else {
            myWordEt.forEach {
                if (it.text.isNullOrEmpty()) it.error = "عليك وضع حرف"
            }
        }
    }
}