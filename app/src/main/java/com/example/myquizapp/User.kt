package com.example.myquizapp

class User {
    var userId = 1L
    var userName = "username"
    var password = "password"
    var email = "email"
    var profilePicture = 1

    constructor (
        userId: Long,
        userName: String,
        password: String,
        email: String,
        profilePicture: Int
    ) {
        this.userId = userId
        this.userName = userName
        this.password = password
        this.email = email
        this.profilePicture = profilePicture
    }

    constructor(userName: String, password: String) {
        this.userName = userName
        this.password = password
    }

    constructor(email: String, password: String, extra: Int) {
        this.email = email
        this.password = password
    }
}