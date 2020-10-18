package com.example.madlevel2task2

data class QuizStatement(
    var quizStatementText: String,
    var quizAnswer: Boolean
) {
    companion object {
        val QUIZ_STATEMENTS = mapOf<String, Boolean>(
            "Apples are never green" to false,
            "It never rains in the Netherlands" to false,
            "King Willem Alexander has red hair" to true,
            "The moon spins around its axis faster than the earth" to false,
            "Apple's iOS runs secretly on Android" to false,
            "Grass is green, just like Android" to true
        )
    }
}