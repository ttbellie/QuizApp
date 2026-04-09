package com.quiz.app.model

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
