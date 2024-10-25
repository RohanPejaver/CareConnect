package com.example.careconnect.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.careconnect.model.User
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.careconnect.model.UserDataState
import com.example.careconnect.screens.search.SearchViewModel
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun SearchContent(viewModel: SearchViewModel, navController: NavController) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight(0.95f)
            .fillMaxWidth()
    ) {
        SetData(viewModel = viewModel, navController)
        Row {
            Spacer(Modifier.fillMaxWidth(0.8f))
            AddConnection { connectionId ->
                viewModel.addConnection(connectionId)
            }
        }
    }
}

@Composable
fun SetData(viewModel: SearchViewModel, navController: NavController) {
    when (val result = viewModel.response.value) {
        is UserDataState.UserLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = my_secondary)
            }
        }
        is UserDataState.UserSuccess -> {
            ShowLazyList(result.data, navController)
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
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error Fetching data",
                )
            }
        }
    }
}

@Composable
fun AddConnection(onClick: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .size(64.dp)
            .background(my_primary, CircleShape)
            .clickable { showDialog = true },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(8.dp)
                .height(32.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Box(
            modifier = Modifier
                .width(32.dp)
                .height(8.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Enter Your Doctor's Connection ID", color = Color.White, fontWeight = FontWeight.ExtraBold) },
            text = {
                TextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = { Text(
                        "Enter Connection ID",
                        color = Color.White.copy(0.95f)
                    ) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.White.copy(0.95f),
                        focusedIndicatorColor = Color.White.copy(0.95f),
                        unfocusedIndicatorColor = Color.White.copy(0.95f)
                    )
                )
            },
            confirmButton = {
                Text(
                    text = "Add",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onClick(textFieldValue)
                        showDialog = false
                    }
                )
            },
            dismissButton = {
                Text(
                    text = "Cancel",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { showDialog = false }
                )
            },
            backgroundColor = my_secondary.copy(0.98f),
        )
    }
}

@Composable
fun ShowLazyList(users: MutableList<User>, navController: NavController) {
    LazyColumn {
        items(users) { user ->
            UserCard(user, navController)
        }
    }
}

