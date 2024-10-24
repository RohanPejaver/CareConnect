package com.example.careconnect.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careconnect.domain.AuthRepository
import com.example.careconnect.domain.ReloadUserResponse
import com.example.careconnect.domain.Response.*
import com.example.careconnect.domain.RevokeAccessResponse
import com.example.careconnect.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set

    private val auth = FirebaseAuth.getInstance()

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

    fun signOut() {
        auth.signOut()
    }

    fun deleteAccount() {
        auth.currentUser?.delete()
    }
}