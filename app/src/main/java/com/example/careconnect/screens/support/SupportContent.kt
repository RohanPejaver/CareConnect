package com.example.careconnect.screens.support

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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun SupportContent(
    navController : NavController,
    modifier : Modifier = Modifier,
    viewModel : SupportViewModel
) {
    Column(
        modifier = modifier
    ){
        MessageList(
            modifier = Modifier
                .weight(1f),
            messageList = viewModel.messageList
        )
        MessageInput(
            onMessageSend = {
                viewModel.sendMessage(it)
            }
        )
    }
}

@Composable
fun MessageInput(
    onMessageSend : (String) -> Unit
) {
    var message by remember {
        mutableStateOf("")
    }

    Row (
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 56.dp, max = 200.dp),
            maxLines = 5,
            value = message,
            onValueChange = {
                message = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = my_primary,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black
            )
        )
        IconButton(
            onClick = {
            if (message.isNotEmpty())
                {
                    onMessageSend(message)
                    message = ""
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
    messageList: List<MessageModel>
) {
    if (messageList.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            WarningCard()
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
                Text(text = "Ask me anything", fontSize = 22.sp, color = Color.Gray)
            }
        }
    } else {
        LazyColumn(
            modifier = modifier,
            reverseLayout = true,
            contentPadding = PaddingValues(8.dp),
        ) {
            items(messageList.reversed()) {
                MessageRow(messageModel = it)
            }
        }
    }
}

@Composable
fun MessageRow(
    messageModel : MessageModel
) {
    val isModel = messageModel.role == "model"

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
                        if (isModel)
                            Alignment.BottomStart
                        else
                            Alignment.BottomEnd
                    )
                    .padding(
                        start =
                        if (isModel)
                            8.dp
                        else
                            30.dp,
                        end =
                        if (isModel)
                            8.dp
                        else
                            30.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        if (isModel)
                            my_primary
                        else
                            my_secondary
                    )
                    .padding(16.dp)
            ) {
                SelectionContainer {
                    Text(
                        text = messageModel.message,
                        fontWeight = FontWeight.W400,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun WarningCard() {
    Card(
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(
            containerColor = my_secondary
        ),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(60.dp)
            .shadow(elevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "IF YOU HAVE AN EMERGENCY, CALL 911 IMMEDIATELY", color = Color.White)
        }
    }
}