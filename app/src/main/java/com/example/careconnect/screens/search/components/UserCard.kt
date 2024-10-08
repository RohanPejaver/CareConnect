package com.example.careconnect.screens.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.careconnect.model.User

@Composable
fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${user.username}",)
            Text(text = "Email: ${user.email}",)
            // Add more fields as needed
        }
    }
}
