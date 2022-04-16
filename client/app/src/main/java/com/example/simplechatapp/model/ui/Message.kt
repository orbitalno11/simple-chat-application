package com.example.simplechatapp.model.ui

data class Message(
    val senderId: String,
    val senderName: String,
    val message: String,
    val sendTime: Long
)