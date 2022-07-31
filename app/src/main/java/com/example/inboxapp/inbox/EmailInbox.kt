package com.example.inboxapp.inbox

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.inboxapp.model.InboxEvent
import com.example.inboxapp.model.InboxState
import com.example.inboxapp.R
import com.example.inboxapp.model.InboxStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInbox(
    modifier: Modifier = Modifier,
    state: InboxState,
    eventListener: (InboxEvent) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 8.dp),
                        text = stringResource(id = R.string.title_inbox, state.content?.size ?: 0),
                        fontWeight = FontWeight.Bold,
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            when (state.status) {
                InboxStatus.LOADING -> {
                    CircularProgressIndicator()
                }
                InboxStatus.ERROR -> {
                    ErrorState(inboxEventListener = eventListener)
                }
                InboxStatus.EMPTY -> {
                    EmptyState(inboxEventListener = eventListener)
                }
                InboxStatus.SUCCESS -> {
                    EmailList(
                        emailList = state.content!!,
                        eventListener = eventListener
                    )
                }
            }
        }
    }

}