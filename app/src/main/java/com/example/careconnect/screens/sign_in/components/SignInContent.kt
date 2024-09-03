package com.example.careconnect.screens.sign_in.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import com.example.careconnect.components.IconLoginButton
import com.example.careconnect.components.Logo
import com.example.careconnect.components.PasswordField
import com.example.careconnect.core.Constants.EMPTY_STRING
import com.example.careconnect.navigation.Screen
import com.example.careconnect.ui.theme.my_primary

@Composable
@ExperimentalComposeUiApi
fun SignInContent(
    signIn: (email: String, password: String) -> Unit,
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
        horizontalAlignment = Alignment.CenterHorizontally,
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
            text = "Welcome",
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 8.dp)
        )
        HeaderTextField(
            text = "Back",
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 8.dp)
        )
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            },
            leadingIcon = Icons.Default.Email
        )
        Spacer(Modifier.height(8.dp))
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue ->
                password = newValue
            },
            leadingIcon = Icons.Default.Lock
        )
        Spacer(Modifier.height(8.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(),
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                androidx.compose.material3.Text(
                    text = "Don't have an account?",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = { navController.navigate(Screen.Signup.route)}) {
                    androidx.compose.material3.Text(text = "Sign Up", color = Color.White)
                }
            }
            TextButton(onClick = { navController.navigate(Screen.Forgot.route) }) {
                androidx.compose.material3.Text(text = "Forgot Password?", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        androidx.compose.material3.Button(
            onClick = {
                keyboard?.hide()
                signIn(email.text, password.text)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = my_primary),
        ) {
            Text(
                text = "Login",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconLoginButton(
            onImageClick = {index ->
                when(index){
                    0 -> {
                        Toast.makeText(context, "Logged in with Google", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(context, "Logged in with Facebook", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(context, "Logged in with X", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onSignUpClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
        )
    }
}