package com.example.careconnect.model

import com.google.firebase.firestore.DocumentReference

data class MessageDetails(
    var message: String = "",
    var sentOn: Long = 0,
    var sentBy: DocumentReference? = null,
    var messageByCurrentUser: Boolean = false
)
