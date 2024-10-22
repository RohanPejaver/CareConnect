package com.example.careconnect.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careconnect.ui.theme.dark_white
import com.example.careconnect.ui.theme.my_secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentCard(
    onClick: () -> Unit,
    text: String
) {
        Card(
            onClick = onClick,
            colors = CardDefaults.cardColors(
                containerColor = dark_white // Example color, replace with your secondary color
            ),
            modifier = Modifier
                .width(180.dp)
                .height(200.dp)
                .shadow(elevation = 8.dp) // Add a shadow with elevation
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                Text(
                    text = text,
                    color = my_secondary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
}
