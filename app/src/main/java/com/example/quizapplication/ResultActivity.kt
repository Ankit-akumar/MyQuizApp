package com.example.quizapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import kotlin.system.exitProcess


class ResultActivity : AppCompatActivity() {
    private var playerScore = 0
    private var unattemptedQuestions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Score gained by user in quiz
        playerScore = intent.getIntExtra("player score", 0)
        unattemptedQuestions = intent.getIntExtra("unattempted questions", 0)

        // display score to user
        tv_correct.text = playerScore.toString()
        tv_unattempted.text = unattemptedQuestions.toString()
        tv_incorrect.text = (10 - playerScore - unattemptedQuestions).toString()
        progress_bar_result.progress = playerScore * 10

        // update score of user in database
        val databaseHandler = DatabaseHandler.getInstance(this)
        databaseHandler?.setUserScore(Constants.CURRENT_USERID, playerScore)

        // Restart quiz with the same category
        btn_play_again.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Start category activity
        btn_new_quiz.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
            finish()
        }

        iv_exit.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Exit Application")
            builder.setMessage("Are you sure you want to exit?")
            builder.setPositiveButton("Yes") { _, _ ->
                run {
                    moveTaskToBack(true)
                    android.os.Process.killProcess(android.os.Process.myPid())
                    exitProcess(1)
                }
            }
            builder.setNegativeButton("No") { dialogInterface, _ -> dialogInterface.cancel() }

            val alertDialog = builder.create()

            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}