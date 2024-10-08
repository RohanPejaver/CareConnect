package com.example.careconnect.core

import com.example.careconnect.model.ChatDetails
import com.example.careconnect.model.ChatDetailsFirestore
import com.example.careconnect.model.MessageDetails
import com.example.careconnect.model.User

class Callbacks {
    interface FirestoreCallbacks {

        fun isTrue() {}

        fun isFalse() {}

        fun userDetails(user: User) {}

        fun onError(message: String) {}

        fun userList(users: List<User>) {}

        fun chatDetails(
            currentChatDetails: ChatDetails,
            currentChatDetailsFirestore: ChatDetailsFirestore,
            otherChatDetails: ChatDetails,
            otherChatDetailsFirestore: ChatDetailsFirestore,
        ) {}

        fun chatList(chatList: List<ChatDetails>, favChatList: List<ChatDetails>) {}

        fun messages(messages: List<MessageDetails>) {}
    }
}
