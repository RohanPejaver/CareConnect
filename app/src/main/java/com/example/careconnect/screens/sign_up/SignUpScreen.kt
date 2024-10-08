package com.example.careconnect.screens.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careconnect.core.Constants.VERIFY_EMAIL_MESSAGE
import com.example.careconnect.core.Utils.Companion.showMessage
import com.example.careconnect.screens.sign_up.components.SignUpContent

@Composable
@ExperimentalComposeUiApi
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController,
    role: String,
) {

    SignUpContent(
        signUp = { email, password, username, role ->
            viewModel.signUpWithEmailAndPassword(email, password, username, role)
        },
        navController = navController,
        role = role,
        viewModel = viewModel
    )
}