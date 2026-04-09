package com.quiz.app.utils

import com.quiz.app.model.Question

class QuizManager(private val questions: List<Question>) {

    var currentIndex: Int = 0
        private set

    var score: Int = 0
        private set

    var isSubmitted: Boolean = false
        private set

    val totalQuestions: Int
        get() = questions.size

    val currentQuestion: Question
        get() = questions[currentIndex]

    val isLastQuestion: Boolean
        get() = currentIndex == questions.size - 1

    val progressPercent: Int
        get() {
            val answered = if (isSubmitted) currentIndex + 1 else currentIndex
            return (answered * 100) / totalQuestions
        }

    fun submitAnswer(selectedIndex: Int): Boolean {
        if (isSubmitted) return false

        isSubmitted = true
        val correct = selectedIndex == currentQuestion.correctAnswerIndex
        if (correct) {
            score++
        }
        return correct
    }

    fun moveToNext(): Boolean {
        if (isLastQuestion) return false

        currentIndex++
        isSubmitted = false
        return true
    }
}
