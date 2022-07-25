package com.example.inboxapp.inbox

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.inboxapp.model.InboxEvent
import com.example.inboxapp.model.InboxState
import com.example.inboxapp.R

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
                        modifier = Modifier.wrapContentSize()
                            .padding(top = 8.dp),
                        text = stringResource(id = R.string.title_inbox, state.content?.size ?: 0),
                        fontWeight = FontWeight.Bold,
                    )
                }
            )
        }
    ) {

    }

}