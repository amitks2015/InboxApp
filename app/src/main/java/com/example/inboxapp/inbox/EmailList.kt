package com.example.inboxapp.inbox

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.inboxapp.R
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

            val dividerAlpha by animateFloatAsState(
                targetValue = if(dismissState.currentValue == DismissValue.Default) {
                    1f
                } else {
                    0f
                },
                animationSpec = tween(300)
            )

            val deleteLabel = stringResource(id = R.string.cd_delete_email)

            SwipeToDismiss(
                modifier = Modifier.semantics {
                    customActions = listOf(
                        CustomAccessibilityAction(deleteLabel) {
                            eventListener(InboxEvent.DeleteEvent(email.id))
                            true
                        }
                    )
                },
                state = dismissState,
                directions = setOf(DismissDirection.StartToEnd),
                dismissThresholds = {
                    FractionalThreshold(0.15f)
                },
                background = {
                    EmailItemBackground(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(emailHeightAnimation),
                        dismissState = dismissState
                    )
                },
                dismissContent = {
                    EmailItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(emailHeightAnimation),
                        dismissState = dismissState,
                        email = email
                    )
                }
            )
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .alpha(dividerAlpha)
            )
        }
    }
}