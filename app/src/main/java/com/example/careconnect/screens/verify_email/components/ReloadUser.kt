package com.example.careconnect.screens.verify_email.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careconnect.screens.profile.ProfileViewModel
import com.example.careconnect.domain.Response.*
import com.example.careconnect.components.ProgressBar
import com.example.careconnect.navigation.Screen

@Composable
fun ReloadUser(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit,
    navController: NavController
) {
    when(val reloadUserResponse = viewModel.reloadUserResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isUserReloaded = reloadUserResponse.data
            LaunchedEffect(isUserReloaded) {
                if (isUserReloaded) {
                    navController.navigate(Screen.PatientHome.route)
                }
            }
        }
        is Failure -> reloadUserResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}