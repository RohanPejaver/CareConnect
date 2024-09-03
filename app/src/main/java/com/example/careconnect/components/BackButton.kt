package com.example.careconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.navigation.Screen

@Composable
fun BackButton(
    screen: Screen,
    navController: NavController
){
    Image(
        painter = painterResource(id = R.drawable.back_arrow),
        contentDescription = "Back Button",
        colorFilter = ColorFilter.tint(Color.White),
        modifier = Modifier

    )
}