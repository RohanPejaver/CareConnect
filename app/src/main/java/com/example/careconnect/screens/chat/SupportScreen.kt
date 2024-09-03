package com.example.careconnect.screens.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.careconnect.screens.chat.ChatViewModel
import com.example.careconnect.screens.chat.ChatScreen

@Composable
fun SupportScreen(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ChatScreen(navController = navController, viewModel = ChatViewModel(), modifier = Modifier.fillMaxSize())

    }
}
