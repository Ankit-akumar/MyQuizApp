package com.example.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapplication.Constants.CURRENT_USERID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}