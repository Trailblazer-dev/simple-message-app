package com.example.simplemessenger

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.simplemessenger.data.Conversation
import com.example.simplemessenger.data.Message
import com.example.simplemessenger.ui.screens.ChatScreen
import com.example.simplemessenger.ui.screens.ConversationListScreen

@Composable
fun MessengerNavHost(navController: NavHostController) {
    // Sample data
    val conversations = remember {
        listOf(
            Conversation(1, "Alice", "Hey, how are you?", "10:30", 2, true),
            Conversation(2, "Bob", "See you tomorrow!", "09:15", 0, false),
            Conversation(3, "Charlie", "Thanks for the help", "Yesterday", 1, true)
        )
    }

    var messages by remember {
        mutableStateOf(
            listOf(
                Message(1, "Hey there!", false, "10:28"),
                Message(2, "Hi! How are you?", true, "10:29"),
                Message(3, "I'm good, thanks!", false, "10:30")
            )
        )
    }

    var selectedContact by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "conversations") {
        composable("conversations") {
            ConversationListScreen(
                conversations = conversations,
                onConversationClick = { id ->
                    selectedContact = conversations.find { it.id == id }?.name ?: ""
                    navController.navigate("chat")
                }
            )
        }
        composable("chat") {
            ChatScreen(
                contactName = selectedContact,
                messages = messages,
                onBackClick = { navController.popBackStack() },
                onSendMessage = { text ->
                    messages = messages + Message(
                        id = messages.size + 1,
                        text = text,
                        isSent = true,
                        time = "Now"
                    )
                }
            )
        }
    }
}