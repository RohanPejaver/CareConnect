package com.example.careconnect.screens.chat

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.careconnect.core.Constants.TAG
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

class ChatViewModel @Inject constructor(): ViewModel(){
    private val db = Firebase.firestore
    private val uid = FirebaseAuth.getInstance().currentUser?.uid

    private var _user = MutableStateFlow<User?>(null)
    var user = _user.asStateFlow()

    val response: MutableState<MessageDataState> = mutableStateOf(MessageDataState.MessageEmpty)

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

    private fun getAllCurrentUserMessages() {
        val tempList = mutableListOf<Message>()
        response.value = MessageDataState.MessageLoading
        if (uid != null) {
            db.collection("chats")
                .document(uid)
                .collection("messages")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        val message = document.toObject(Message::class.java)
                        tempList.add(message)
                    }
                    response.value = MessageDataState.MessageSuccess(tempList)
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }

    private fun findUserByConnectionId(connectionId: String, onResult: (User?) -> Unit){
        val db = FirebaseFirestore.getInstance()

        db.collection("users")
            .whereEqualTo("connectionId", connectionId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val userDocument = querySnapshot.documents[0]
                    val user = userDocument.toObject(User::class.java)

                    onResult(user)
                } else {
                    onResult(null)
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error querying users by connectionId", e)
                onResult(null)
            }
    }

    private fun getAllContactedUserMessages(connectionId: String) {
        val tempList = mutableListOf<Message>()
        response.value = MessageDataState.MessageLoading

        findUserByConnectionId(connectionId) { user ->
            if (user != null) {
                val db = FirebaseFirestore.getInstance()
                if (uid != null) {
                    db.collection("chats")
                        .document(user.userId)
                        .collection("connections")
                        .document(uid)
                        .collection("messages")
                        .get()
                        .addOnSuccessListener { querySnapshot ->
                            for (document in querySnapshot) {
                                val message = document.toObject(Message::class.java)
                                tempList.add(message)
                            }
                            response.value = MessageDataState.MessageSuccess(tempList)
                        }
                        .addOnFailureListener { exception ->
                            Log.w(TAG, "Error getting documents: ", exception)
                        }
                } else {
                    Log.e("SearchViewModel", "User ID is null")
                }
            } else {
                Log.d("SearchViewModel", "User with Connection ID not found")
            }
        }
    }
}