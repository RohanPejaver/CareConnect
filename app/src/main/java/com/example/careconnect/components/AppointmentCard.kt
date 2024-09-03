package com.example.careconnect.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.careconnect.ui.theme.my_secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentCard(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(), // Ensure the parent Box takes the full screen size
        contentAlignment = Alignment.Center // Center the Card within the parent Box
    ) {
        Card(
            onClick = onClick,
            colors = CardDefaults.cardColors(
                containerColor = my_secondary // Example color, replace with your secondary color
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(60.dp)
                .shadow(elevation = 8.dp) // Add a shadow with elevation
                .align(alignment = Alignment.Center) // Center the Card horizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center // Center the Text inside the Card
            ) {
                Text(text = "Find Your Next Appointment", color = Color.White)
            }
        }
    }
}
