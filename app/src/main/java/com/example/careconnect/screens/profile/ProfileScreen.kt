package com.example.careconnect.screens.profile

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.careconnect.screens.profile.components.ProfileContent
import com.example.careconnect.screens.profile.components.RevokeAccess

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val user by viewModel.user.collectAsStateWithLifecycle()

    ProfileContent(
        signOut = {
            viewModel.signOut()
        },
        deleteAccount = {
            viewModel.deleteAccount()
        },
        username = "${user?.username}",
        email = "${user?.email}"
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}