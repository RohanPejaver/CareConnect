package com.example.careconnect.screens.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.model.Message
import com.example.careconnect.model.MessageDataState
import com.example.careconnect.navigation.Screen
import com.example.careconnect.screens.chat.ChatViewModel
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun ChatContent(
    viewModel: ChatViewModel,
    connectedUserId: String,
    navController: NavController
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(connectedUserId) {
        viewModel.getMessages(connectedUserId)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(scrollState, orientation = Orientation.Vertical)
        ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()){
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // Align vertically centered
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Back Button",
                    colorFilter = ColorFilter.tint(my_secondary),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate(Screen.Search.route) }
                )
                Spacer(modifier = Modifier.weight(0.75f))
                Text(
                    text = viewModel.connectedUser.value?.username ?: "Chat",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = my_secondary,
                    modifier = Modifier.align(Alignment.CenterVertically) // Ensures vertical alignment
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // The message list should take up as much space as possible
        SetData(
            viewModel = viewModel,
            connectedUserId = connectedUserId,
            modifier = Modifier
        )
    }
    Column {
        Spacer(modifier = Modifier.fillMaxSize(0.9f))
        MessageInput(
            viewModel = viewModel,
            connectedUserId = connectedUserId
        )
    }
}


@Composable
fun SetData(
    viewModel: ChatViewModel,
    connectedUserId: String,
    modifier: Modifier,
) {

    LaunchedEffect(connectedUserId) {
        viewModel.getMessages(connectedUserId)
    }

    when (val result = viewModel.response.value) {
        is MessageDataState.MessageLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = my_secondary)
            }
        }
        is MessageDataState.MessageSuccess -> {
            MessageList(messageList = result.data)
        }
        is MessageDataState.MessageFailure -> {
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
                    text = "You Don't Have any Chats Yet!", color = my_primary, fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MessageList(
    modifier: Modifier = Modifier,
    messageList: List<Message>
) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true,
        contentPadding = PaddingValues(8.dp),
    ) {
        items(messageList.reversed()) {
            MessageRow(message = it)
        }
    }
}

@Composable
fun MessageRow(
    message: Message
) {
    val isCurrentUser = message.messageByCurrentUser

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
                        if (!isCurrentUser)
                            Alignment.BottomStart
                        else
                            Alignment.BottomEnd
                    )
                    .padding(
                        start =
                        if (!isCurrentUser)
                            8.dp
                        else
                            30.dp,
                        end =
                        if (!isCurrentUser)
                            8.dp
                        else
                            30.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        if (!isCurrentUser)
                            my_primary
                        else
                            my_secondary
                    )
                    .padding(16.dp)
            ) {
                SelectionContainer {
                    androidx.compose.material3.Text(
                        text = message.message,
                        fontWeight = FontWeight.W400,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun MessageInput(
    viewModel: ChatViewModel,
    connectedUserId: String,
) {
    var messageText by remember { mutableStateOf("") } // Track the input text

    Row(
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // OutlinedTextField to capture message input
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 56.dp, max = 200.dp),
            maxLines = 4, // Allow multiple lines if needed
            value = messageText, // Bind the state to the text field
            onValueChange = { messageText = it },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = my_primary,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black
            ),
            placeholder = { Text(text = "Type a message") }
        )

        // Send button
        IconButton(
            onClick = {
                if (messageText.isNotEmpty()) {
                    // Create a new message object
                    val message = Message(
                        message = messageText,
                        sentOn = System.currentTimeMillis().toString(), // Example timestamp, use actual method
                        messageByCurrentUser = true
                    )

                    // Call viewModel to send the message
                    viewModel.sendMessages(connectedUserId, message)

                    // Clear the text field after sending the message
                    messageText = ""
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