package com.example.myquizapp

class User constructor(
    val user_id:Long,
    val UserName:String,
    val Password:String,
    val email:String,
    val securityQuestion:String,
    val securityAnswer:String,
    val profilePicture:Int
)