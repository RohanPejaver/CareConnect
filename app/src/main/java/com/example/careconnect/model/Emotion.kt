package com.example.careconnect.model

data class Emotion(
    var emotion: String,
    var date: String
) {
    constructor(): this("", "")
}

sealed class EmotionDataState {
    class EmotionSuccess(val data: MutableList<Emotion>) : EmotionDataState()
    class EmotionFailure(val message: String) : EmotionDataState()
    object EmotionLoading : EmotionDataState()
    object EmotionEmpty : EmotionDataState()
}