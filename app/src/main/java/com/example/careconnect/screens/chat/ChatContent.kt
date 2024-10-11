package com.example.careconnect.screens.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.careconnect.model.Message
import com.example.careconnect.model.MessageDataState
import com.example.careconnect.ui.theme.my_primary

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

@Composable
fun MessageSender(message: Message, onMessageSend: (String)-> Unit, viewModel: ChatViewModel){
    Row (
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = message.message,
            onValueChange = {
                viewModel.sendMessage(message)
            }
        )
    }
}