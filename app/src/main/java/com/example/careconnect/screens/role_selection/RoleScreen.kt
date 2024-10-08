package com.example.careconnect.screens.role_selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.components.HeaderTextField
import com.example.careconnect.components.Logo
import com.example.careconnect.navigation.Screen
import com.example.careconnect.ui.theme.my_primary

@Composable
fun RoleScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = my_primary.copy(alpha = 0.6f))
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Back Button",
                colorFilter = ColorFilter.tint(
                    Color.White
                ),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate(Screen.Choose.route) }
            )
            Logo()
        }
        Spacer(modifier = Modifier.size(20.dp))
        HeaderTextField(
            text = "Select",
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 8.dp)
        )
        HeaderTextField(
            text = "Role",
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.Gray.copy(alpha = 0.2f))
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.patient),
                    contentDescription = "Patient",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .clickable(onClick = { navController.navigate("signup/patient") })
                        .size(120.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Sign up as a patient",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.Gray.copy(alpha = 0.2f))
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.doctor),
                    contentDescription = "Doctor",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .clickable(onClick = { navController.navigate("signup/doctor") })
                        .size(120.dp)
                )
            }
            Text(
                "Sign up as a registered physician",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

