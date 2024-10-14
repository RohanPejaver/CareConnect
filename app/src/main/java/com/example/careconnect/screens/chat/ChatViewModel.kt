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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ChatViewModel @Inject constructor(): ViewModel() {
    private val db = Firebase.firestore

    private var _connectedUser = MutableStateFlow<User?>(null)
    var connectedUser = _connectedUser.asStateFlow()
    private val connectedUserUid = connectedUser.value?.userId

    private var _user = MutableStateFlow<User?>(null)
    var user = _user.asStateFlow()
    private val uid = FirebaseAuth.getInstance().currentUser?.uid

    val currentUserResponse: MutableState<MessageDataState> = mutableStateOf(MessageDataState.MessageEmpty)
    val connectedUserResponse: MutableState<MessageDataState> = mutableStateOf(MessageDataState.MessageEmpty)

    private val _currentUserMessages = MutableLiveData<List<Message>>()
    val currentUserMessages: LiveData<List<Message>> get() = _currentUserMessages

    private val _connectedUserMessages = MutableLiveData<List<Message>>()
    val connectedUserMessages: LiveData<List<Message>> get() = _connectedUserMessages


    init {
        getUser()
    }

    private fun getUser() {
        val db = Firebase.firestore
        val auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid

        if (uid != null) {
            db.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    _user.value = documentSnapshot.toObject(User::class.java)
                }
        }
    }

    init {
        getAllCurrentUserMessages()
    }

    fun getAllCurrentUserMessages() {
        val tempList = mutableListOf<Message>()
        currentUserResponse.value = MessageDataState.MessageLoading
        if (uid != null && connectedUserUid != null) {
            db.collection("chats")
                .document(uid)
                .collection("connections")
                .document(connectedUserUid)
                .collection("myMessages")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        val message = document.toObject(Message::class.java)
                        tempList.add(message)
                    }
                    _currentUserMessages.value = tempList
                    currentUserResponse.value = MessageDataState.MessageSuccess(tempList)
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }

    init {
        getAllConnectedUserMessages()
    }

    fun getAllConnectedUserMessages() {
        val tempList = mutableListOf<Message>()
        connectedUserResponse.value = MessageDataState.MessageLoading
        if (uid != null && connectedUserUid != null) {
            db.collection("chats")
                .document(uid)
                .collection("connections")
                .document(connectedUserUid)
                .collection("connectionMessages")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        val message = document.toObject(Message::class.java)
                        tempList.add(message)
                    }
                    _connectedUserMessages.value = tempList
                    connectedUserResponse.value = MessageDataState.MessageSuccess(tempList)                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }

    fun sendMessage(message: Message){
        if(uid != null && connectedUserUid != null){
            // Current UID document + myMessage
            db.collection("chats")
                .document(uid)
                .collection("connections")
                .document(connectedUserUid)
                .collection("myMessages")
                .document(message.sentOn.toString())
                .set(message)
                .addOnSuccessListener {
                    Log.i(TAG, "Message Sent Successfully")
                }

            // Current UID document + connectionMessage
            db.collection("chats")
                .document(uid)
                .collection("connections")
                .document(connectedUserUid)
                .collection("connectionMessages")
                .document(message.sentOn.toString())
                .set(message)
                .addOnSuccessListener {
                    Log.i(TAG, "Message Sent Successfully")
                }

            // Connection UID document + myMessage (connectedUser's connectionMessage)
            db.collection("chats")
                .document(connectedUserUid)
                .collection("connections")
                .document(uid)
                .collection("connectionMessages")
                .document(message.sentOn.toString())
                .set(message)
                .addOnSuccessListener {
                    Log.i(TAG, "Message Sent Successfully")
                }

            // Connection UID document + connectionMessage (connectedUser's myMessage)
            db.collection("chats")
                .document(connectedUserUid)
                .collection("connections")
                .document(uid)
                .collection("myMessages")
                .document(message.sentOn.toString())
                .set(message)
                .addOnSuccessListener {
                    Log.i(TAG, "Message Sent Successfully")
                }
        }
    }
}