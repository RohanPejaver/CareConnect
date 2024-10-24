package com.example.careconnect.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameField(
    username: TextFieldValue,
    onUsernameValueChange: (newValue: TextFieldValue) -> Unit,
    leadingIcon: ImageVector? = null,
    leadingIconTint: Color = Color.White,
) {
    val focusRequester = FocusRequester()

    OutlinedTextField(
        modifier = Modifier
            .focusRequester(focusRequester)
            .fillMaxWidth(),
        value = username,
        onValueChange = { newValue ->
            onUsernameValueChange(newValue)
        },
        label = {
            Text("Username", color = Color.White)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(android.graphics.Color.parseColor("#FFFFFF")),
            unfocusedBorderColor = Color(android.graphics.Color.parseColor("#FFFFFF")),
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            cursorColor = Color.White
        ),
        leadingIcon = {
            if (leadingIcon != null) {
                Box {
                    androidx.compose.material3.Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = leadingIconTint
                    )
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        coroutineContext.job.invokeOnCompletion {
            focusRequester.requestFocus()
        }
    }
}