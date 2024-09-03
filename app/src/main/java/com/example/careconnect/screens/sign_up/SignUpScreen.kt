package com.example.careconnect.screens.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careconnect.core.Constants.VERIFY_EMAIL_MESSAGE
import com.example.careconnect.core.Utils.Companion.showMessage
import com.example.careconnect.screens.sign_up.components.SendEmailVerification
import com.example.careconnect.screens.sign_up.components.SignUp
import com.example.careconnect.screens.sign_up.components.SignUpContent

@Composable
@ExperimentalComposeUiApi
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    SignUpContent(
        signUp = { email, password, username ->
            viewModel.signUpWithEmailAndPassword(email, password, username)
        },
        navController = navController
    )

    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            showMessage(context, VERIFY_EMAIL_MESSAGE)
        }
    )

    SendEmailVerification()
}