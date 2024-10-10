package com.example.careconnect.model

data class User(
    val username: String,
    val email: String,
    val role: String,
    val userId: String,
    val connectionId: String
) {
    constructor(): this("", "", "", "", "")
}

sealed class UserDataState {
    class UserSuccess(val data: MutableList<User>) : UserDataState()
    class UserFailure(val message: String) : UserDataState()
    object UserLoading : UserDataState()
    object UserEmpty : UserDataState()
}