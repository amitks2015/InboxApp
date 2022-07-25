package com.example.inboxapp.inbox

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inboxapp.InboxViewModel
import com.example.inboxapp.ui.theme.InboxAppTheme

@Composable
fun Inbox() {
    val viewModel: InboxViewModel = viewModel()
    InboxAppTheme {
        EmailInbox(
            modifier = Modifier.fillMaxSize(),
            state = viewModel.uiState.collectAsState().value,
            eventListener = viewModel::handleEvent
        )
    }
    LaunchedEffect(Unit) {
        viewModel.loadContent()
    }
}

@Preview
@Composable
fun PreviewInbox() {
    Inbox()
}