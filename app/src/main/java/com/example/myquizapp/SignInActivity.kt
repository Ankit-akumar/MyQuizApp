package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val pairUsernameAndEmail: Pair<Boolean, Boolean> = databaseHandler.doesUserExists(user)

            if (pairUsernameAndEmail.first or pairUsernameAndEmail.second) {
                // TODO : Get real user and send real User ID
                // go to main activity
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userID", user.userId)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }
        }

        tv_sign_up_here.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}