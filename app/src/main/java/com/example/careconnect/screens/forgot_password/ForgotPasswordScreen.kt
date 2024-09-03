package com.example.careconnect.screens.forgot_password

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careconnect.core.Constants.RESET_PASSWORD_MESSAGE
import com.example.careconnect.core.Utils.Companion.showMessage
import com.example.careconnect.screens.forgot_password.components.ForgotPassword
import com.example.careconnect.screens.forgot_password.components.ForgotPasswordContent

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current


    ForgotPasswordContent(
        sendPasswordResetEmail = { email ->
            viewModel.sendPasswordResetEmail(email)
        },
        navController
    )

    ForgotPassword(
        showResetPasswordMessage = {
            showMessage(context, RESET_PASSWORD_MESSAGE)
        },
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}