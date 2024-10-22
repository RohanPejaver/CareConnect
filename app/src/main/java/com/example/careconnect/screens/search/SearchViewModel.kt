package com.example.careconnect.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.careconnect.core.Constants.TAG
import com.example.careconnect.model.Message
import com.example.careconnect.model.User
import com.example.careconnect.model.UserDataState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val db = Firebase.firestore
    private val uid = FirebaseAuth.getInstance().currentUser?.uid

    private var _user = MutableStateFlow<User?>(null)
    var user = _user.asStateFlow()

    val response: MutableState<UserDataState> = mutableStateOf(UserDataState.UserEmpty)

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
        getAllUsers()
    }

    private fun getAllUsers() {
        val tempList = mutableListOf<User>()
        response.value = UserDataState.UserLoading
        if (uid != null) {
            db.collection("chats")
                .document(uid)
                .collection("connections")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        val user = document.toObject(User::class.java)
                        if (user.userId != uid) {
                            tempList.add(user)
                        }
                    }
                    response.value = UserDataState.UserSuccess(tempList)
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

    @SuppressLint("SuspiciousIndentation")
    fun addConnection(connectionId: String) {
        val inputUser = User(
            user.value?.email ?: "",
            user.value?.username ?: "",
            user.value?.role ?: "",
            user.value?.userId ?: "",
            user.value?.connectionId ?: ""
        )
            findUserByConnectionId(connectionId) { user ->
            if (user != null) {
                val db = FirebaseFirestore.getInstance()
                if (uid != null) {
                    db.collection("chats")
                        .document(uid)
                        .collection("connections")
                        .document(user.userId)
                        .set(user)
                        .addOnSuccessListener {
                            getAllUsers()
                            Log.d("SearchViewModel", "Connection added successfully")
                        }
                        .addOnFailureListener { e ->
                            Log.e("SearchViewModel", "Error adding connection", e)
                        }

                    db.collection("chats")
                        .document(user.userId)
                        .collection("connections")
                        .document(uid)
                        .set(inputUser)
                        .addOnSuccessListener {
                            getAllUsers()
                            Log.d("SearchViewModel", "Connection added successfully")
                        }
                        .addOnFailureListener { e ->
                            Log.e("SearchViewModel", "Error adding connection", e)
                        }

                    db.collection("chats")
                        .document(uid)
                        .collection("connections")
                        .document(uid)
                        .collection("messages")
                        .document("example")
                        .set(Message("This is your first message"))
                        .addOnSuccessListener {
                            getAllUsers()
                            Log.d("SearchViewModel", "Connection added successfully")
                        }
                        .addOnFailureListener { e ->
                            Log.e("SearchViewModel", "Error adding connection", e)
                        }

                    db.collection("chats")
                        .document(uid)
                        .collection("connections")
                        .document(user.userId)
                        .collection("messages")
                        .document("example")
                        .set(Message("This is your first message"))
                        .addOnSuccessListener {
                            getAllUsers()
                            Log.d("SearchViewModel", "Connection added successfully")
                        }
                        .addOnFailureListener { e ->
                            Log.e("SearchViewModel", "Error adding connection", e)
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
