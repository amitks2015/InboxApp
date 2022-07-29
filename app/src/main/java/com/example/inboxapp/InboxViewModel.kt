package com.example.inboxapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inboxapp.model.EmailFactory
import com.example.inboxapp.model.InboxEvent
import com.example.inboxapp.model.InboxState
import com.example.inboxapp.model.InboxStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class InboxViewModel: ViewModel() {

    val uiState = MutableStateFlow(InboxState())

    fun loadContent() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                status = InboxStatus.LOADING
            )
            delay(2000)
            uiState.value = uiState.value.copy(
                status = InboxStatus.SUCCESS,
                content = EmailFactory.makeEmailList()
            )
        }
    }

    private fun deleteEmail(id: String) {
        uiState.value = uiState.value.copy(
            content = EmailFactory.makeEmailList().filter {
                it.id != id
            }
        )
    }

    fun handleEvent(event: InboxEvent) {
        when(event) {
            is InboxEvent.RefreshEvent -> loadContent()
            is InboxEvent.DeleteEvent -> deleteEmail(event.id)
        }
    }
}