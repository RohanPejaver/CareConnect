package com.example.careconnect.screens.diagnostic.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.example.careconnect.ui.theme.my_primary
import kotlinx.coroutines.job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    input: TextFieldValue,
    onInputValueChange: (newValue: TextFieldValue) -> Unit,
    text: String,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        value = input,
        onValueChange = { newValue ->
            onInputValueChange(newValue)
        },
        label = {
            androidx.compose.material.Text(text, color = Color.Gray)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = my_primary,
            unfocusedBorderColor = my_primary,
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black,
            cursorColor = my_primary
        ),
    )
}