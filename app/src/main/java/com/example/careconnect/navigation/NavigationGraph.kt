package com.example.careconnect.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.careconnect.screens.chat.ChatScreen
import com.example.careconnect.screens.chat.ChatViewModel
import com.example.careconnect.screens.choose.ChooseScreen
import com.example.careconnect.screens.help.HelpScreen
import com.example.careconnect.screens.library.LibraryScreen
import com.example.careconnect.screens.settings.SettingsScreen
import com.example.careconnect.screens.support.SupportScreen
import com.example.careconnect.screens.forgot_password.ForgotPasswordScreen
import com.example.careconnect.screens.home.DoctorHomeScreen
import com.example.careconnect.screens.home.PatientHomeScreen
import com.example.careconnect.screens.sign_in.SignInScreen
import com.example.careconnect.screens.profile.ProfileScreen
import com.example.careconnect.screens.role_selection.RoleScreen
import com.example.careconnect.screens.search.SearchScreen
import com.example.careconnect.screens.search.SearchViewModel
import com.example.careconnect.screens.sign_up.SignUpScreen

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SetupNavGraph (
    navController : NavHostController,
) {
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
            route = Screen.Role.route
        ) {
            RoleScreen(
                navController
            )
        }
        composable(
            route = "signup/{role_param}",
            arguments = listOf(
                navArgument("role_param") {
                    type = NavType.StringType
                }
            )
        ) {
            val roleParam = it.arguments?.getString("role_param") ?: ""
            SignUpScreen(role = roleParam, navController = navController)
        }
        composable(
            route = Screen.Search.route
        ) {
            SearchScreen(viewModel = SearchViewModel(), navController)
        }
        composable(
            route = "chat/{connectionId}",
            arguments = listOf(
                navArgument("connectionId") {
                    type = NavType.StringType
                }
            )
        ) {
            val connectionParam = it.arguments?.getString("connection_param") ?: ""
            ChatScreen(connectionId = connectionParam, navController = navController, viewModel = ChatViewModel())
        }
        composable(
            route = Screen.PatientHome.route
        ) {
            PatientHomeScreen(navController)
        }
        composable(
            route = Screen.DoctorHome.route
        ) {
            DoctorHomeScreen(navController)
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
            route = Screen.Forgot.route
        ) {
            ForgotPasswordScreen(navController = navController)
        }
    }
}