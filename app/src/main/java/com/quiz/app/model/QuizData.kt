package com.quiz.app.model

object QuizData {
    fun getQuestions(): List<Question> {
        return listOf(
            Question(
                questionText = "What does CPU stand for?",
                options = listOf(
                    "Central Processing Unit",
                    "Computer Personal Unit",
                    "Central Program Utility",
                    "Control Processing User"
                ),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "Which language is officially recommended for Android development?",
                options = listOf(
                    "Python",
                    "Swift",
                    "Kotlin",
                    "C#"
                ),
                correctAnswerIndex = 2
            ),
            Question(
                questionText = "What is the purpose of a ProgressBar in a quiz app?",
                options = listOf(
                    "To play music",
                    "To show quiz progress",
                    "To close the app",
                    "To change screen colour"
                ),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "Which component is used to move from one Activity to another in Android?",
                options = listOf(
                    "Intent",
                    "Fragment",
                    "Button",
                    "TextView"
                ),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "What is used to store simple key-value data in Android?",
                options = listOf(
                    "RecyclerView",
                    "SharedPreferences",
                    "ConstraintLayout",
                    "Manifest"
                ),
                correctAnswerIndex = 1
            ),
            )
    }
}
