package com.example.careconnect.screens.verify_email

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careconnect.components.TopBar
import com.example.careconnect.core.Constants.EMAIL_NOT_VERIFIED_MESSAGE
import com.example.careconnect.core.Constants.VERIFY_EMAIL_SCREEN
import com.example.careconnect.core.Utils.Companion.showMessage
import com.example.careconnect.screens.profile.ProfileViewModel
import com.example.careconnect.screens.profile.components.RevokeAccess
import com.example.careconnect.screens.verify_email.components.ReloadUser
import com.example.careconnect.screens.verify_email.components.VerifyEmailContent

@Composable
fun VerifyEmailScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit,
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    VerifyEmailContent(
        reloadUser = {
            viewModel.reloadUser()
        },
        navController = navController
    )

    ReloadUser(
        navigateToHomeScreen = {
            if (viewModel.isEmailVerified) {
                navigateToHomeScreen()
            } else {
                showMessage(context, EMAIL_NOT_VERIFIED_MESSAGE)
            }
        },
        navController = navController
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}