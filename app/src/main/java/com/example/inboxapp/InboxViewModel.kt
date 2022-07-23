package com.example.inboxapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inboxapp.model.EmailFactory
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
}