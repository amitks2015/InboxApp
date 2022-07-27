package com.example.inboxapp.inbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun ErrorState(
    modifier: Modifier = Modifier,
    inboxEventListener: (InboxEvent) -> Unit
) {
    Column(
        modifier = modifier.padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.message_content_error),
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = {
                inboxEventListener(InboxEvent.RefreshEvent)
            }
        ) {
            Text(text = stringResource(id = R.string.try_again_label))
        }
    }
}

@Preview
@Composable
fun PreviewErrorState() {
    InboxAppTheme {
        ErrorState(inboxEventListener = {})
    }
}