package com.example.careconnect.screens.home.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.careconnect.model.User
import com.example.careconnect.model.VaccinationData

@Composable
fun ShowUserList(users: MutableList<User>, navController: NavController) {
    LazyRow {
        items(users) { user ->
            HomeUserCard(user, navController)
        }
    }
}

@Composable
fun ShowVaccinationList(vaccineInfo: MutableList<VaccinationData>, navController: NavController) {
    LazyRow {
        items(vaccineInfo) { vaccine ->
            HomeVaccineCard(vaccine, navController)
        }
    }
}