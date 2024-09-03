package com.example.careconnect.screens.verify_email.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.components.Logo
import com.example.careconnect.core.Constants.ALREADY_VERIFIED
import com.example.careconnect.core.Constants.SPAM_EMAIL
import com.example.careconnect.navigation.Screen
import com.example.careconnect.ui.theme.my_primary

@Composable
fun VerifyEmailContent(
    reloadUser: () -> Unit,
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
                .background(color = my_primary.copy(alpha = 0.5f))
        )
    }
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Image(painter = painterResource(id = R.drawable.back_arrow), contentDescription = "Back Button", colorFilter = ColorFilter.tint(
            Color.White),
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.navigate(Screen.Choose.route) }
        )
        Logo()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "CareConnect Logo",
            modifier = Modifier.size(200.dp),
        )
        androidx.compose.material3.Text(
            text = "CareConnect",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier.clickable {
                reloadUser()
            },
            text = "Already Verified?",
            fontSize = 20.sp,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "If not, check the spam folder for the verification link",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}