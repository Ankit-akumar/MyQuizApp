package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnSignUp.setOnClickListener {
            // Adding the user into database
            val user = User(
                1,
                et_username.text.toString(),
                et_password.text.toString(),
                et_email.text.toString(),
                R.drawable.profile_pic_default_big
            )

            val databaseHandler = DatabaseHandler.getInstance(this)
            when (databaseHandler.addUser(user)) {
                -1L -> Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT)
                    .show()
                -2L -> Toast.makeText(this, "This email is already registered", Toast.LENGTH_SHORT)
                    .show()
                -3L -> Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
                else -> {
                    // Set user as the current logged in user
                    databaseHandler.setCurrentUser(user)
                    Log.d("AFTER SET CURRENT USER","done")
                    // Go to main activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        tvBtnLoginHere.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}