package com.example.careconnect.screens.sign_up.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.components.EmailField
import com.example.careconnect.components.HeaderTextField
import com.example.careconnect.components.Logo
import com.example.careconnect.components.PasswordField
import com.example.careconnect.components.UsernameField
import com.example.careconnect.core.Constants.EMPTY_STRING
import com.example.careconnect.domain.User
import com.example.careconnect.navigation.Screen
import com.example.careconnect.ui.theme.my_primary

@Composable
@ExperimentalComposeUiApi
fun SignUpContent(
    signUp: (email: String, password: String, username: String) -> Unit,
    navController: NavController
) {
    var username by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
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
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )

    val keyboard = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Image(painter = painterResource(id = R.drawable.back_arrow), contentDescription = "Back Button", colorFilter = ColorFilter.tint(
                Color.White),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate(Screen.Choose.route) }
            )
            Logo()
        }
        Spacer(modifier = Modifier.size(20.dp))
        HeaderTextField(
            text = "Create",
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 8.dp)
        )
        HeaderTextField(
            text = "Account",
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        UsernameField(
            username = username,
            onUsernameValueChange = { newValue ->
                username = newValue
            },
            leadingIcon = Icons.Default.Person
        )
        Spacer(modifier = Modifier.height(8.dp))
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            },
            leadingIcon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue ->
                password = newValue
            },
            leadingIcon = Icons.Default.Lock
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                keyboard?.hide()
                signUp(email.text, password.text, username.text)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = my_primary),
            ) {
            Text(
                text = "Sign Up",
                color = Color.White
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Already have an account?",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
            TextButton(onClick = {
                navController.navigate(Screen.SignIn.route)
            }) {
                Text(text = "Login", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}
