package com.example.inboxapp.model

sealed class InboxEvent {
    object RefreshEvent: InboxEvent()
    data class DeleteEvent(val id: String): InboxEvent()
}