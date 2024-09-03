package com.example.careconnect.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.careconnect.screens.chat.ChatViewModel
import com.example.careconnect.screens.chat.ChatScreen
import com.example.careconnect.screens.choose.ChooseScreen
import com.example.careconnect.screens.help.HelpScreen
import com.example.careconnect.screens.home.HomeScreen
import com.example.careconnect.screens.library.LibraryScreen
import com.example.careconnect.screens.settings.SettingsScreen
import com.example.careconnect.screens.sign_up.SignUpScreen
import com.example.careconnect.screens.chat.SupportScreen
import com.example.careconnect.screens.forgot_password.ForgotPasswordScreen
import com.example.careconnect.screens.sign_in.SignInScreen
import com.example.careconnect.screens.profile.ProfileScreen
import com.example.careconnect.screens.sign_up.SignUpViewModel
import com.example.careconnect.screens.verify_email.VerifyEmailScreen

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SetupNavGraph (
    navController : NavHostController,
    chatViewModel: ChatViewModel,
) {

    val viewModel: SignUpViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Choose.route
    ) {
        composable(
            route = Screen.Choose.route
        ) {
            ChooseScreen(navController)
        }
        composable(
            route = Screen.SignIn.route
        ) {
            SignInScreen(navController = navController)
        }
        composable(
            route = Screen.Signup.route
        ) {
            SignUpScreen(navController = navController)
        }
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Settings.route
        ) {
            SettingsScreen(navController)
        }
        composable(
            route = Screen.Profile.route
        ) {
            ProfileScreen()
        }
        composable(
            route = Screen.Help.route
        ) {
            HelpScreen(navController)
        }
        composable(
            route = Screen.Support.route
        ) {
            SupportScreen(navController)
        }
        composable(
            route = Screen.Library.route
        ) {
            LibraryScreen(navController)
        }
        composable(
            route = Screen.Chat.route
        ) {
            ChatScreen(navController, viewModel = chatViewModel)
        }
        composable(
            route = Screen.Verify.route
        ) {
            VerifyEmailScreen(navigateToHomeScreen = { /*TODO*/ }, navController = navController)
        }
        composable(
            route = Screen.Forgot.route
        ) {
            ForgotPasswordScreen(navController = navController)
        }
    }
}