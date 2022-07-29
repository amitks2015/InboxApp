package com.example.inboxapp.inbox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inboxapp.model.Email

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emailList: List<Email>
) {
    LazyColumn(modifier = modifier) {
        items(emailList, key = {item ->  item.id}) { email ->
            SwipeToDismiss(
                state = DismissState(DismissValue.Default),
                directions = setOf(DismissDirection.StartToEnd),
                dismissThresholds = {
                    FractionalThreshold(0.15f)
                },
                background ={},
                dismissContent = {
                    EmailItem(email = email)
                }
            )
        }
    }
}

@Composable
fun EmailItem(
    modifier: Modifier = Modifier,
    email: Email
) {
    Card(
        modifier = modifier.padding(16.dp)
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