package com.example.myquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myquizapp.Constants.CURRENT_USER
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseHandler = DatabaseHandler.getInstance(this)
        // TODO: NOT WORKING
//        CURRENT_USER = databaseHandler.getCurrentUser()
//        textView5.text = CURRENT_USER.userName
    }
}