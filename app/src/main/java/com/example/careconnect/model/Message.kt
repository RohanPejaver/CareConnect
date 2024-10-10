package com.example.careconnect.model

import com.google.firebase.firestore.DocumentReference

data class Message(
    var message: String = "",
    var sentOn: Long = 0,
    var sentBy: DocumentReference? = null,
    var messageByCurrentUser: Boolean = false
)

sealed class MessageDataState {
    class MessageSuccess(val data: MutableList<Message>) : MessageDataState()
    class MessageFailure(val message: String) : MessageDataState()
    object MessageLoading : MessageDataState()
    object MessageEmpty : MessageDataState()
}
