package com.example.careconnect.screens.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careconnect.core.Utils.Companion.showMessage
import com.example.careconnect.screens.sign_in.components.SignIn
import com.example.careconnect.screens.sign_in.components.SignInContent

@Composable
@ExperimentalComposeUiApi
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    SignInContent(
        signIn = { email, password ->
            viewModel.signInWithEmailAndPassword(email, password)
        },
        navController = navController
    )

    SignIn(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}