package com.example.careconnect.screens.support

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careconnect.model.GeminiModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class SupportViewModel : ViewModel() {

    val messageList by lazy {
        mutableStateListOf<GeminiModel>()
    }

    val generativeModel : GenerativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "AIzaSyArWsA4exGsRbxdWiiHzN2FTqG7XjpuiD8"
    )

    fun sendMessage(question : String) {
        viewModelScope.launch {
            try {
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(
                            it.role
                        ) {
                            text(it.message)
                        }
                    }.toList()
                )
                messageList.add(GeminiModel(question, "user"))
                messageList.add(GeminiModel("Typing...", "model"))

                val response = chat.sendMessage(question)
                messageList.removeLast()
                messageList.add(GeminiModel(response.text.toString(), "model"))
            }
            catch (e : Exception) {
                messageList.removeLast()
                messageList.add(GeminiModel("Error : " +e.message.toString(), "model"))
            }


        }
    }

}