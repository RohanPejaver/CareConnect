package com.example.careconnect.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.careconnect.R
import com.example.careconnect.model.Message
import com.example.careconnect.model.MessageDataState
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun ChatContent(viewModel: ChatViewModel) {
    Column{
        SetData(viewModel = viewModel)
    }
}

@Composable
fun SetData(viewModel: ChatViewModel) {
    viewModel.getAllConnectedUserMessages()
    viewModel.getAllCurrentUserMessages()

    when (val result = viewModel.currentUserResponse.value) {
        is MessageDataState.MessageLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = my_secondary)
            }
        }
        is MessageDataState.MessageSuccess -> {
            Column{
                MessageList(
                    modifier = Modifier
                        .weight(1f),
                    connectedUserMessages = viewModel.connectedUserMessages.value,
                    currentUserMessages = viewModel.currentUserMessages.value
                )
                MessageInput(viewModel)
            }
        }
        is MessageDataState.MessageFailure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "fail")
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "idk")
            }
        }
    }
}

@Composable
fun MessageInput(
    viewModel: ChatViewModel
) {
    var message by remember {
        mutableStateOf(Message())
    }
    Row (
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 56.dp, max = 200.dp),
            maxLines = 5,
            value = message.message,
            onValueChange = {newMessage ->
                message = message.copy(message = newMessage)
            },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = my_primary,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black
            )
        )
        IconButton(
            onClick = {
                if (message.message.isNotEmpty()) {
                    viewModel.sendMessage(message)
                    message = message.copy(message = "")
                }
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send your message"
            )
        }
    }
}

@Composable
fun MessageList(
    modifier: Modifier = Modifier,
    currentUserMessages: List<Message>?,
    connectedUserMessages: List<Message>?
) {
    val allMessages = (currentUserMessages?.plus(connectedUserMessages))

    if (currentUserMessages != null && connectedUserMessages != null) {
        if (currentUserMessages.isEmpty() && connectedUserMessages.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        modifier = Modifier.size(60.dp),
                        painter = painterResource(id = R.drawable.question_answer),
                        contentDescription = "Chat icon",
                        tint = my_secondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Say Hello!", fontSize = 22.sp, color = Color.Gray)
                }
            }
        } else {
            LazyColumn(
                modifier = modifier,
                reverseLayout = true,
                contentPadding = PaddingValues(8.dp),
            ) {
                items(allMessages as List<*>) { message ->
                    ChatRow(
                        message = message as Message
                    )
                }
            }
        }
    }
}

@Composable
fun ChatRow(
    message: Message,
) {
    val isPatient = message.messageByCurrentUser

    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .align(
                        if (isPatient)
                            Alignment.BottomStart
                        else
                            Alignment.BottomEnd
                    )
                    .padding(
                        start =
                        if (isPatient)
                            8.dp
                        else
                            30.dp,
                        end =
                        if (isPatient)
                            8.dp
                        else
                            30.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        if (isPatient)
                            my_primary
                        else
                            my_secondary
                    )
                    .padding(16.dp)
            ) {
                if(isPatient)
                    SelectionContainer {
                        Text(
                            text = message.message,
                            fontWeight = FontWeight.W400,
                            color = Color.White
                        )
                    } else {
                    SelectionContainer {
                        Text(
                            text = message.message,
                            fontWeight = FontWeight.W400,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

