package com.example.careconnect.screens.chat


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.careconnect.screens.chat.components.ChatContent

@Composable
fun ChatScreen(
    connectedUserId: String,
    viewModel: ChatViewModel,
    navController: NavController
) {
    ChatContent(viewModel, connectedUserId, navController)
}