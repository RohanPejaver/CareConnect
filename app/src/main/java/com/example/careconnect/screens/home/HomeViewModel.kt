package com.example.careconnect.screens.home

import androidx.lifecycle.ViewModel
import com.example.careconnect.domain.AuthRepository
import com.example.careconnect.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    private var _user = MutableStateFlow<User?>(null)
    var user = _user.asStateFlow()

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

}