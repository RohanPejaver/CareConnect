package com.example.careconnect.components

import android.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careconnect.ui.theme.dark_white
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun HomeCard(
    onClick: () -> Unit,
    text: String,
    image: Int
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
            Column(
                modifier = Modifier
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    color = my_secondary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Navigate through the screen",
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(my_primary)
                )
            }
        }
}
