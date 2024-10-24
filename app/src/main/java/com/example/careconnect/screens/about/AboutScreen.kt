package com.example.careconnect.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.navigation.Screen
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun AboutScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, top = 20.dp)
        ){
            Image(painter = painterResource(id = R.drawable.back_arrow), contentDescription = "Back Button", colorFilter = ColorFilter.tint(
                my_primary
            ),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate(Screen.Settings.route) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "About Us",
            color = my_secondary,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Thank you so much for downloading CareConnect :) This application has been developed in order to provide everyone, no matter what background they may have, with any general medical/ healthcare questions they may have. This app is developed by Rohan Rao Pejaver with assistance from Rishab Rao Pejaver and Dr. Mrudula Deshpande. \n\nSpecial thanks to Prathima Rao and Ajay Rao <3",
            color = my_secondary,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.fillMaxHeight(1f))
    }
}