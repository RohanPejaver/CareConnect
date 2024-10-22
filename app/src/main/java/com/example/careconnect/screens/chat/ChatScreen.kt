package com.example.careconnect.screens.chat


import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ChatScreen(
    connectedUserId: String,
    viewModel: ChatViewModel
) {
    ChatContent(viewModel, connectedUserId)
}