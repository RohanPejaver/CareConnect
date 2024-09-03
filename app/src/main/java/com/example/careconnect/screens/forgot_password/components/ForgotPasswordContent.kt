package com.example.careconnect.screens.forgot_password.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.components.EmailField
import com.example.careconnect.components.Logo
import com.example.careconnect.core.Constants.EMPTY_STRING
import com.example.careconnect.core.Constants.RESET_PASSWORD_BUTTON
import com.example.careconnect.navigation.Screen
import com.example.careconnect.ui.theme.my_primary

@Composable
fun ForgotPasswordContent(
    sendPasswordResetEmail: (email: String) -> Unit,
    navController: NavController
) {
    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )

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
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "CareConnect Logo",
            modifier = Modifier.size(200.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Enter your email address in order to receive instructions on how to reset your password",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(16.dp))
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            },
            leadingIcon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(
            onClick = {
                sendPasswordResetEmail(email.text)
            },
            modifier = Modifier
                .width(100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = my_primary),
        ) {
            Text(
                text = "Reset",
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}