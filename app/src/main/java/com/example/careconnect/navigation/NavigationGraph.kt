package com.example.careconnect.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.graphics.rotationMatrix
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.careconnect.screens.chat.ChatScreen
import com.example.careconnect.screens.chat.ChatViewModel
import com.example.careconnect.screens.choose.ChooseScreen
import com.example.careconnect.screens.diagnostic.DiagnosticOne
import com.example.careconnect.screens.diagnostic.DiagnosticThree
import com.example.careconnect.screens.diagnostic.DiagnosticTwo
import com.example.careconnect.screens.diagnostic.DiagnosticViewModel
import com.example.careconnect.screens.diagnostic.ResultScreen
import com.example.careconnect.screens.help.HelpScreen
import com.example.careconnect.screens.library.LibraryScreen
import com.example.careconnect.screens.settings.SettingsScreen
import com.example.careconnect.screens.support.SupportScreen
import com.example.careconnect.screens.forgot_password.ForgotPasswordScreen
import com.example.careconnect.screens.home.DoctorHomeScreen
import com.example.careconnect.screens.home.PatientHomeScreen
import com.example.careconnect.screens.mental.MentalScreen
import com.example.careconnect.screens.mental.MentalViewModel
import com.example.careconnect.screens.sign_in.SignInScreen
import com.example.careconnect.screens.profile.ProfileScreen
import com.example.careconnect.screens.role_selection.RoleScreen
import com.example.careconnect.screens.search.SearchScreen
import com.example.careconnect.screens.search.SearchViewModel
import com.example.careconnect.screens.sign_up.SignUpScreen
import com.example.careconnect.vaccination.VaccinationScreen
import com.example.careconnect.vaccination.VaccinationViewModel

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
            route = "chat/{connectedUserId}",
            arguments = listOf(
                navArgument("connectedUserId") {
                    type = NavType.StringType
                }
            )
        ) {
            val connectionParam = it.arguments?.getString("connectedUserId") ?: ""
            ChatScreen(connectedUserId = connectionParam, viewModel = ChatViewModel())
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
        composable(
            route = Screen.DiagnosticOne.route
        ) {
            DiagnosticOne(viewmodel = DiagnosticViewModel(), navController)
        }
        composable(
            route = Screen.DiagnosticTwo.route
        ) {
            DiagnosticTwo(viewmodel = DiagnosticViewModel(), navController)
        }
        composable(
            route = Screen.DiagnosticThree.route
        ) {
            DiagnosticThree(viewmodel = DiagnosticViewModel(), navController)
        }
        composable(
            route = Screen.Result.route
        ) {
            ResultScreen(viewModel = DiagnosticViewModel(), navController = navController)
        }
        composable(
            route = Screen.Vaccination.route
        ) {
            VaccinationScreen(viewModel = VaccinationViewModel())
        }
        composable(
            route = Screen.Mental.route
        ) {
            MentalScreen(viewmodel = MentalViewModel())
        }
    }
}