package com.example.myquizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myquizapp.Constants.CURRENT_USER
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseHandler = DatabaseHandler.getInstance(this)
        CURRENT_USER = databaseHandler.getCurrentUser()

    }
}