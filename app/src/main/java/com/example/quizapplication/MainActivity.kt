package com.example.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapplication.Constants.CURRENT_USERID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var quizzes: ArrayList<Quiz>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvQuiz = rv_quizzes as RecyclerView
        quizzes = Quiz.createQuizList(20)
        val adapter = QuizAdapter(quizzes)
        rvQuiz.adapter = adapter
        rvQuiz.layoutManager = LinearLayoutManager(this)
    }
}