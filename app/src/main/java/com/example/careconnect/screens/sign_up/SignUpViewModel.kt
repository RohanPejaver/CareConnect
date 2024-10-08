package com.example.careconnect.screens.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careconnect.domain.AuthRepository
import com.example.careconnect.domain.SignUpResponse
import com.example.careconnect.domain.Response.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepository,
): ViewModel() {
    var signUpResponse by mutableStateOf<SignUpResponse>(Success(false))
        private set

    fun signUpWithEmailAndPassword(email: String, password: String, username: String, role: String) = viewModelScope.launch {
        signUpResponse = Loading
        signUpResponse = repo.firebaseSignUpWithEmailAndPassword(email, password, username, role)
    }
}