package com.example.careconnect.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careconnect.screens.search.components.SearchContent

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(), navController: NavController, modifier: Modifier) {

   SearchContent(viewModel = viewModel, navController)
}