package com.quiz.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quiz.app.R

class MainActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var startButton: Button

    companion object {
        const val EXTRA_USER_NAME = "extra_user_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput = findViewById(R.id.editTextName)
        startButton = findViewById(R.id.buttonStart)

        // Restore name if returning from results
        intent.getStringExtra(EXTRA_USER_NAME)?.let {
            nameInput.setText(it)
        }

        startButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra(EXTRA_USER_NAME, name)
            startActivity(intent)
        }
    }
}
