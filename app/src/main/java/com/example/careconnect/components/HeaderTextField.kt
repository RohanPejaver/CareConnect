package com.example.careconnect.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.careconnect.ui.theme.my_primary

@Composable
fun HeaderTextField(
    text : String,
    modifier : Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFFFFFF),
        modifier = modifier
    )

}