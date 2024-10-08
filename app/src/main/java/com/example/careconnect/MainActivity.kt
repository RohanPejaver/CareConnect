package com.example.careconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.careconnect.navigation.Screen
import com.example.careconnect.navigation.SetupNavGraph
import com.example.careconnect.screens.support.SupportViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val supportViewModel = ViewModelProvider(this)[SupportViewModel::class.java]

                navController = rememberNavController()
                SetupNavGraph(navController = navController)
                AuthState()
        }
    }

    @Composable
    private fun AuthState() {
        val user by viewModel.user.collectAsStateWithLifecycle()

        val isUserSignedOut = viewModel.getAuthState().collectAsState().value
        if (isUserSignedOut) {
            NavigateToChooseScreen()
        } else {
            if (user?.role.equals("patient")) {
                NavigateToPatientHomeScreen()
            }
            else if(user?.role.equals("doctor")) {
                NavigateToDoctorHomeScreen()
            }
            else {

            }
        }
    }

    @Composable
    private fun NavigateToVerifyEmailScreen() = navController.navigate(Screen.Choose.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToChooseScreen() = navController.navigate(Screen.Choose.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToPatientHomeScreen() = navController.navigate(Screen.PatientHome.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToDoctorHomeScreen() = navController.navigate(Screen.DoctorHome.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }
}




