package com.example.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_category.*
class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        cv_aptitude_test.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            Constants.CATEGORY_SELECTED = "aptitude"
            startActivity(intent)
        }

        cv_reasoning_test.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            Constants.CATEGORY_SELECTED = "reasoning"
            startActivity(intent)
        }

        cv_verbal_test.setOnClickListener {
            Toast.makeText(this, "Coming soon...",Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, MainActivity::class.java)
//            Constants.CATEGORY_SELECTED = "verbal ability"
//            startActivity(intent)
        }
    }
}