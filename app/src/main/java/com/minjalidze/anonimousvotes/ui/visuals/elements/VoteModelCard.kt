package com.minjalidze.anonimousvotes.ui.visuals.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.minjalidze.anonimousvotes.R
import com.minjalidze.anonimousvotes.data.models.VoteAnswer
import com.minjalidze.anonimousvotes.data.models.VoteModel
import com.minjalidze.anonimousvotes.ui.theme.DGreen
import com.minjalidze.anonimousvotes.ui.theme.DLighterBlack

@Composable
fun VoteModelCard(voteModel: VoteModel, onClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard (
        elevation = CardDefaults.cardElevation( defaultElevation = 12.dp ),
        modifier = Modifier
            .height(270.dp)
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .clickable(enabled = true, onClick = { onClick() })) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painterResource(R.drawable.ic_quiz), "iconVote", modifier = Modifier
                    .padding(start = 16.dp))
                Spacer(Modifier.weight(4f))
                Text(
                    text = voteModel.title,
                    textAlign = TextAlign.Center,
                    fontWeight = Bold
                )
                Spacer(Modifier.weight(4f))
                Column {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Filled.MoreVert, "")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Поделиться опросом") },
                            onClick = { onClick(); expanded = false }
                        )
                    }
                }
            }
            LazyColumn (modifier =
            Modifier
                .height(200.dp)
                .padding(8.dp)
                .background(Color.Transparent)) {
                items(voteModel.items) {
                    AnswerCard(it)
                }
            }
        }
    }
}

@Composable
fun AnswerCard(voteAnswer: VoteAnswer) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation( defaultElevation = 12.dp ),
        modifier = Modifier
            .padding(top = 4.dp)
            .height(40.dp)
            .fillMaxWidth()) {
        TextButton(onClick = { /*TODO: VOTE*/ },
            Modifier
                .background(DLighterBlack)
                .fillMaxWidth()
                .fillMaxHeight()) {
            Text(text = voteAnswer.title, fontWeight = Bold, color = DGreen, textAlign = TextAlign.Left)
        }
    }
}