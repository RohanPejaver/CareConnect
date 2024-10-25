package com.example.careconnect.screens.support

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.careconnect.screens.support.components.SupportContent

@Composable
fun SupportScreen(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        SupportContent(viewModel = SupportViewModel(), modifier = Modifier.fillMaxSize())

    }
}
