package com.example.careconnect.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.model.UserDataState
import com.example.careconnect.model.VaccinationDataState
import com.example.careconnect.navigation.Screen
import com.example.careconnect.screens.home.HomeViewModel
import com.example.careconnect.screens.search.components.ShowLazyList
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun SetHomeData(viewModel: HomeViewModel, navController: NavController) {
    when (val result = viewModel.response.value) {
        is UserDataState.UserLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(0.5f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = my_secondary)
            }
        }
        is UserDataState.UserSuccess -> {
            ShowUserList(result.data, navController)
        }
        is UserDataState.UserFailure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = result.message,
                )
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Connected Patients Yet!", color = my_primary, fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = { navController.navigate(Screen.Search.route)}) {
                    Text(text = "Connect to Patient", color = my_primary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun SetVaccinationData(viewModel: HomeViewModel, navController: NavController) {
    when (val result = viewModel.vaccinationResponse.value) {
        is VaccinationDataState.VaccinationDataLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(0.5f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = my_secondary)
            }
        }
        is VaccinationDataState.VaccinationDataSuccess -> {
            ShowVaccinationList(result.data, navController)
        }
        is VaccinationDataState.VaccinationDataFailure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = result.message,
                )
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Patient Vaccinations Retrieved Yet!", color = my_primary, fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = { navController.navigate(Screen.Search.route)}) {
                    Text(text = "Connect to Patient", color = my_primary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}