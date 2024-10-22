package com.example.careconnect.screens.chat

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.careconnect.core.Constants.TAG
import com.example.careconnect.model.GeminiModel
import com.example.careconnect.model.Message
import com.example.careconnect.model.MessageDataState
import com.example.careconnect.model.User
import com.example.careconnect.model.UserDataState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import javax.inject.Inject

class ChatViewModel @Inject constructor(): ViewModel() {
    val db = Firebase.firestore
    val uid = FirebaseAuth.getInstance().currentUser?.uid

    val response: MutableState<MessageDataState> = mutableStateOf(MessageDataState.MessageEmpty)

    fun getMessages(connectedUserId: String) {
        val tempList = mutableListOf<Message>()
        response.value = MessageDataState.MessageLoading
        if (uid != null) {
            db.collection("chats")
                .document(uid)
                .collection("connections")
                .document(connectedUserId)
                .collection("messages")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val message = document.toObject(Message::class.java)
                        if (message != null) {
                            tempList.add(message)
                        }
                    }
                    response.value = MessageDataState.MessageSuccess(tempList)
                    Log.d(TAG, tempList[0].message)
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }
}