package com.example.careconnect.screens.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careconnect.screens.search.components.SearchContent

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(), navController: NavController) {

   SearchContent(viewModel = viewModel, navController)
}