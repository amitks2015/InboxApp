package com.example.inboxapp.inbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inboxapp.R
import com.example.inboxapp.model.InboxEvent
import com.example.inboxapp.ui.theme.InboxAppTheme

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    inboxEventListener: (InboxEvent) -> Unit
) {
    Column(
        modifier = modifier.padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.message_content_empty),
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                inboxEventListener(InboxEvent.RefreshEvent)
            }
        ) {
            Text(text = stringResource(id = R.string.check_again_label))
        }
    }
}

@Preview
@Composable
fun PreviewEmptyState() {
    InboxAppTheme {
        EmptyState(inboxEventListener = {})
    }
}