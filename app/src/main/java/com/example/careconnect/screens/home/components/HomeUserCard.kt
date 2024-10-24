package com.example.careconnect.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.careconnect.model.User
import com.example.careconnect.model.VaccinationData
import com.example.careconnect.navigation.Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeUserCard(user: User, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp)
            .height(65.dp),
        elevation = 4.dp,
        onClick = { navController.navigate(Screen.Search.route) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${user.username}")
            Text(text = "Email: ${user.email}")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeVaccineCard(vaccinationData: VaccinationData, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp)
            .height(65.dp),
        elevation = 4.dp,
        onClick = { navController.navigate(Screen.Search.route) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Date Of Birth: ${vaccinationData.dateOfBirth}")
            Text(text = "Vaccinations Needed: ${vaccinationData.vaccinationsNeeded}")
        }
    }
}
