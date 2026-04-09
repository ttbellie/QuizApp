package com.quiz.app.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.quiz.app.R
import com.quiz.app.model.QuizData
import com.quiz.app.utils.QuizManager

class QuizActivity : AppCompatActivity() {

    private lateinit var manager: QuizManager
    private lateinit var userName: String

    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var questionTitle: TextView
    private lateinit var questionText: TextView
    private lateinit var optionButtons: List<Button>
    private lateinit var submitButton: Button
    private lateinit var nextButton: Button

    private var selectedIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        userName = intent.getStringExtra(MainActivity.EXTRA_USER_NAME) ?: ""
        manager = QuizManager(QuizData.getQuestions())

        bindViews()
        setupListeners()
        displayQuestion()
    }

    private fun bindViews() {
        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.textProgress)
        questionTitle = findViewById(R.id.textQuestionTitle)
        questionText = findViewById(R.id.textQuestionDetail)
        optionButtons = listOf(
            findViewById(R.id.buttonOption1),
            findViewById(R.id.buttonOption2),
            findViewById(R.id.buttonOption3),
            findViewById(R.id.buttonOption4)
        )
        submitButton = findViewById(R.id.buttonSubmit)
        nextButton = findViewById(R.id.buttonNext)
    }

    private fun setupListeners() {
        optionButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (!manager.isSubmitted) {
                    selectedIndex = index
                    highlightSelected(index)
                }
            }
        }

        submitButton.setOnClickListener {
            if (selectedIndex == -1 || manager.isSubmitted) return@setOnClickListener
            manager.submitAnswer(selectedIndex)
            showAnswerFeedback()
            updateProgress()
            submitButton.isEnabled = false
            nextButton.isEnabled = true
        }

        nextButton.setOnClickListener {
            if (!manager.isSubmitted) return@setOnClickListener

            if (manager.isLastQuestion) {
                goToResults()
            } else {
                manager.moveToNext()
                selectedIndex = -1
                displayQuestion()
            }
        }
    }

    private fun displayQuestion() {
        val q = manager.currentQuestion

        questionTitle.text = "Question ${manager.currentIndex + 1}"
        questionText.text = q.questionText

        optionButtons.forEachIndexed { index, button ->
            button.text = q.options[index]
            button.setBackgroundColor(Color.parseColor("#E0E0E0"))
            button.setTextColor(Color.BLACK)
            button.isEnabled = true
        }

        submitButton.isEnabled = true
        nextButton.isEnabled = false
        updateProgress()

        nextButton.text = if (manager.isLastQuestion) "Finish" else "Next"
    }

    private fun highlightSelected(index: Int) {
        optionButtons.forEachIndexed { i, button ->
            button.setBackgroundColor(
                if (i == index) Color.parseColor("#BBDEFB") else Color.parseColor("#E0E0E0")
            )
            button.setTextColor(Color.BLACK)
        }
    }

    private fun showAnswerFeedback() {
        val correctIdx = manager.currentQuestion.correctAnswerIndex

        optionButtons[correctIdx].setBackgroundColor(Color.parseColor("#4CAF50"))
        optionButtons[correctIdx].setTextColor(Color.WHITE)

        if (selectedIndex != correctIdx) {
            optionButtons[selectedIndex].setBackgroundColor(Color.parseColor("#F44336"))
            optionButtons[selectedIndex].setTextColor(Color.WHITE)
        }

        optionButtons.forEach { it.isEnabled = false }
    }

    private fun updateProgress() {
        val percent = manager.progressPercent
        progressBar.progress = percent

        val completed = if (manager.isSubmitted) manager.currentIndex + 1 else manager.currentIndex
        progressText.text = "$completed/${manager.totalQuestions}"
    }

    private fun goToResults() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(MainActivity.EXTRA_USER_NAME, userName)
        intent.putExtra(ResultActivity.EXTRA_SCORE, manager.score)
        intent.putExtra(ResultActivity.EXTRA_TOTAL, manager.totalQuestions)
        startActivity(intent)
        finish()
    }
}
