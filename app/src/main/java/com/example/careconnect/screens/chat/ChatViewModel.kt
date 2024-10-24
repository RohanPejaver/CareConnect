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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

import java.text.SimpleDateFormat
import java.util.Locale

class ChatViewModel @Inject constructor(): ViewModel() {
    val db = Firebase.firestore
    val uid = FirebaseAuth.getInstance().currentUser?.uid

    private var _connectedUser = MutableStateFlow<User?>(null)
    var connectedUser = _connectedUser.asStateFlow()

    val response: MutableState<MessageDataState> = mutableStateOf(MessageDataState.MessageEmpty)

    // Function to format time
    private fun getCurrentTime(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) // Format as per your needs
        return sdf.format(currentTimeMillis)
    }

    fun findConnectedUser(connectedUserId: String){
        db.collection("users")
            .document(connectedUserId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                _connectedUser.value = documentSnapshot.toObject(User::class.java)
                Log.d(TAG, "User found")
            }
            .addOnFailureListener {
                Log.w(TAG, "No user found")
            }
    }

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
                        if (message != null && message.message != "This is your first message") {
                            tempList.add(message)
                        }
                    }
                    response.value = MessageDataState.MessageSuccess(tempList)
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }

    fun sendMessages(connectedUserId: String, message: Message) {
        if(uid != null){
            // Assign current time to message's sentOn
            message.sentOn = getCurrentTime()

            db.collection("chats")
                .document(uid)
                .collection("connections")
                .document(connectedUserId)
                .collection("messages")
                .document(message.sentOn) // Use sentOn as the document ID
                .set(message)
                .addOnSuccessListener {
                    Log.d(TAG, message.sentOn)
                    getMessages(connectedUserId)
                }
                .addOnFailureListener {
                    Log.wtf(TAG, "Failed to send message")
                }
        }
    }
}
