package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnSignIn.setOnClickListener {
            // Create user according to required constructor
            val user: User = if (et_username.text.toString().contains('.')) {
                User(et_username.text.toString(), et_password.text.toString(), 0)
            } else {
                User(et_username.text.toString(), et_password.text.toString())
            }

            // Check if user exists
            val databaseHandler = DatabaseHandler.getInstance(this)
            val pairUsernameAndEmail = databaseHandler.doesUserExists(user)

            if (pairUsernameAndEmail.first or pairUsernameAndEmail.second) {
                // Set user as the current logged in user
                if(databaseHandler.setCurrentUser(user)) Log.d("Cursor set", "Cursor is set")
                else Log.d("Cursor set","failed")

                // go to main activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }
        }

        tv_sign_up_here.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}