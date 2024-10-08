package com.example.careconnect.model

import com.google.firebase.firestore.DocumentReference

data class ChatDetails(
    var lastMessage: String = "",
    var lastMessageSentOn: Long = 0,
    var lastMessageSentBy: DocumentReference? = null,
    var chatCreatedOn: Long = 0,
    var chatCreatedBy: DocumentReference? = null,
    var chatIsFavourite: Boolean = false,
    var chatLastMessageRead: Boolean = false,
    var members: List<DocumentReference>? = null,
    var chatIsAGroup: Boolean = false,
    var lastMessageSentOnString: String = "",
    var isLastMessageByCurrentUser: Boolean = false,
    var membersUser: ArrayList<User> = ArrayList(),
    var singleChatListDetails: User = User()
)