package com.example.inboxapp.inbox

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.inboxapp.model.Email
import com.example.inboxapp.model.InboxEvent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emailList: List<Email>,
    eventListener: (InboxEvent) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(emailList, key = {item ->  item.id}) { email ->
            var isEmailItemDismissed by remember { mutableStateOf(false) }
            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if(it == DismissValue.DismissedToEnd) {
                        isEmailItemDismissed = true
                    }
                    true
                }
            )

            val emailHeightAnimation by animateDpAsState(
                targetValue = if(isEmailItemDismissed) 0.dp else 120.dp,
                animationSpec = tween(300),
                finishedListener = {
                    eventListener(InboxEvent.DeleteEvent(email.id))
                }
            )

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(DismissDirection.StartToEnd),
                dismissThresholds = {
                    FractionalThreshold(0.15f)
                },
                background = {
                    EmailItemBackground(
                        modifier = Modifier.fillMaxWidth()
                            .height(emailHeightAnimation),
                        dismissState = dismissState
                    )
                },
                dismissContent = {
                    EmailItem(
                        modifier = Modifier.fillMaxWidth()
                        .height(emailHeightAnimation),
                        dismissState = dismissState,
                        email = email
                    )
                }
            )
        }
    }
}