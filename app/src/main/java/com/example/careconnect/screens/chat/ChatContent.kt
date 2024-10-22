package com.example.careconnect.screens.chat

import android.util.Log
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.core.Constants.TAG
import com.example.careconnect.model.GeminiModel
import com.example.careconnect.model.Message
import com.example.careconnect.model.MessageDataState
import com.example.careconnect.model.User
import com.example.careconnect.model.UserDataState
import com.example.careconnect.screens.search.SearchViewModel
import com.example.careconnect.screens.search.components.ShowLazyList
import com.example.careconnect.screens.search.components.UserCard
import com.example.careconnect.screens.support.WarningCard
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun ChatContent(
    viewModel: ChatViewModel,
    connectedUserId: String,
) {
    SetData(viewModel = viewModel, connectedUserId)
}

@Composable
fun SetData(viewModel: ChatViewModel, connectedUserId: String) {

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
                    text = "Error Fetching data",
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