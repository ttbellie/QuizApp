package com.quiz.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.quiz.app.R

class ResultActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SCORE = "extra_score"
        const val EXTRA_TOTAL = "extra_total"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra(MainActivity.EXTRA_USER_NAME) ?: ""
        val score = intent.getIntExtra(EXTRA_SCORE, 0)
        val total = intent.getIntExtra(EXTRA_TOTAL, 0)

        val congratsText = findViewById<TextView>(R.id.textCongrats)
        val scoreText = findViewById<TextView>(R.id.textScore)
        val takeNewQuizButton = findViewById<Button>(R.id.buttonTakeNewQuiz)
        val finishButton = findViewById<Button>(R.id.buttonFinish)

        congratsText.text = "Congratulations $userName!"
        scoreText.text = "YOUR SCORE: $score/$total"

        takeNewQuizButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_USER_NAME, userName)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        finishButton.setOnClickListener {
            finishAffinity()
        }
    }
}
