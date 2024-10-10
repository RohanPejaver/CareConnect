package com.example.careconnect.screens.chat

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.careconnect.model.MessageDataState

@Composable
fun SetData(viewModel: ChatViewModel) {
    when (val result = viewModel.response.value) {
        is MessageDataState.MessageLoading -> {
            Text(text = "loading")
        }
        is MessageDataState.MessageSuccess -> {

        }
        is MessageDataState.MessageFailure -> {

        }
        else -> {

        }
    }
}

@Composable
fun ShowMessages() {

}