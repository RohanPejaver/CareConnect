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

sealed class DataState {
    class UserSuccess(val data: MutableList<User>) : DataState()
    class UserFailure(val message: String) : DataState()
    object UserLoading : DataState()
    object UserEmpty : DataState()
}