package com.example.careconnect.screens.diagnostic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.navigation.Screen
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary
import kotlinx.coroutines.delay


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    viewModel: DiagnosticViewModel,
    navController: NavController
) {
    // Collect the state from the ViewModel
    val generatedOutput = viewModel.textGenerationResult.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = generatedOutput.value.toString(),
            color = my_secondary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Button(
            onClick = {
                navController.navigate(Screen.Library.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(containerColor = my_secondary),
        ) {
            androidx.compose.material3.Text(
                text = "Go Back",
                color = Color.White
            )
        }
    }
}
