package com.example.quizapplication

class Quiz(val id: Long = 0, val name: String, val desc: String, val image: Int) {
    companion object {
        fun createQuizList(numQuiz: Int): ArrayList<Quiz> {
            val quizList = ArrayList<Quiz>()

            for (i in 1..numQuiz) {
                when {
                    i % 3 == 0 -> quizList.add(
                        Quiz(
                            name = "Science Quiz",
                            desc = "Test the Einstein in you!",
                            image = R.drawable.science_quiz
                        )
                    )
                    i % 2 == 0 -> quizList.add(
                        Quiz(
                            name = "Interesting Facts",
                            desc = "Did you know?",
                            image = R.drawable.facts_quiz
                        )
                    )
                    else -> quizList.add(
                        Quiz(
                            name = "Maths Quiz",
                            desc = "I am the easiest one...",
                            image = R.drawable.math_quiz
                        )
                    )
                }
            }
            return quizList
        }
    }
}