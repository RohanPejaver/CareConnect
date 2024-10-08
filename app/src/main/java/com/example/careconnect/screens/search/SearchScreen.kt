package com.example.careconnect.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.careconnect.screens.search.components.SearchContent
import com.example.careconnect.screens.search.components.SetData

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val context = LocalContext.current

   SearchContent(viewModel = viewModel)
}