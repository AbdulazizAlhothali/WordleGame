package com.example.wordle.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.wordle.R
import com.example.wordle.data.NewWord
import com.example.wordle.databinding.ActivityMain3Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity3 : AppCompatActivity() {
    private val viewModel by viewModels<MyWordleVm>()
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main3)

        binding.apply {
            playBtn.setOnClickListener {
                val i = Intent(this@MainActivity3, MainActivity::class.java)
                startActivity(i)
            }

            addWord.setOnClickListener {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity3)
                builder.setTitle("اضف كلمة جديدة")
                val input = EditText(this@MainActivity3)
                input.hint = "ادخل كلمة"
                input.inputType = InputType.TYPE_CLASS_TEXT
                builder.setView(input)
                builder.setPositiveButton("اضف") { _, _ ->
                    val newWord = input.text.toString()
                    if (newWord.length == 5) {
                        viewModel.addWord(NewWord(name = newWord))
                        Toast.makeText(
                            this@MainActivity3,
                            "تم اضافة الكلمة",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity3,
                            "لم تضاف الكلمة, يجب ان تكون الكلمة من خمسة حروف",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                builder.setNegativeButton("الغاء") { dialog, _ -> dialog.cancel() }
                builder.show()
            }
        }
    }
}