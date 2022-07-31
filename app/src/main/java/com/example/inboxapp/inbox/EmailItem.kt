package com.example.inboxapp.inbox

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inboxapp.R
import com.example.inboxapp.model.Email

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailItem(
    modifier: Modifier = Modifier,
    dismissState: DismissState,
    email: Email
) {
    val elevation by animateDpAsState(
        targetValue = if(dismissState.dismissDirection == null) {
            0.dp
        } else {
            4.dp
        }
    )

    Card(
        modifier = modifier.padding(16.dp),
        elevation = elevation
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ) {
            Text(
                text = email.subject,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = email.message,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailItemBackground(
    modifier: Modifier = Modifier,
    dismissState: DismissState
) {
    val backgroundColor by animateColorAsState(
        targetValue = when(dismissState.targetValue) {
            DismissValue.DismissedToEnd -> androidx.compose.material3.MaterialTheme.colorScheme.error
            else -> androidx.compose.material3.MaterialTheme.colorScheme.surface
        },
        animationSpec = tween()
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 20.dp)
    ) {
        if(dismissState.currentValue == DismissValue.Default) {
            val iconColor by animateColorAsState(
                targetValue = when(dismissState.targetValue) {
                    DismissValue.DismissedToEnd -> androidx.compose.material3.MaterialTheme.colorScheme.onError
                    else -> androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                },
                animationSpec = tween()
            )

            val iconScale by animateFloatAsState(
                targetValue = when(dismissState.targetValue) {
                    DismissValue.DismissedToEnd -> 1f
                    else -> 0.75f
                }
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .scale(iconScale),
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.cd_delete_email),
                tint = iconColor,
            )
        }
    }
}