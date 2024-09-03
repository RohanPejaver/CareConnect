package com.example.careconnect.domain

import androidx.compose.ui.text.input.TextFieldValue

data class User(
    val username: String,
    val email: String
) {
    constructor(): this("", "")
}