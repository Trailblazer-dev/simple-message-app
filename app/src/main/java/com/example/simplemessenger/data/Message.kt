package com.example.simplemessenger.data



data class Message(
    val id: Int,
    val text: String,
    val isSent: Boolean,
    val time: String
)

data class Conversation(
    val id: Int,
    val name: String,
    val lastMessage: String,
    val time: String,
    val unread: Int,
    val isOnline: Boolean
)
