package com.example.simplechatapp.model.ui

data class ChatDetail(
    val participant: Participant,
    val messages: List<Message>
) {
    data class Participant(
        val name: String
    )
}
