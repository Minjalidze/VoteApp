package com.minjalidze.anonimousvotes.ui.visuals.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.minjalidze.anonimousvotes.ui.theme.DBlue
import com.minjalidze.anonimousvotes.ui.theme.DGray
import com.minjalidze.anonimousvotes.ui.theme.DLighterBlack

@Composable
fun VoteModelCard(voteModel: VoteModel, onClick: () -> Unit) {
    ElevatedCard (
        elevation = CardDefaults.cardElevation( defaultElevation = 12.dp ),
        modifier = Modifier
            .height(300.dp)
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
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.MoreVert, "")
                }
            }
            LazyColumn (modifier =
            Modifier
                .height(190.dp)
                .padding(8.dp)
                .background(Color.Transparent)) {
                items(voteModel.items) {
                    AnswerCard(it)
                }
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Проголосовал(и) ${voteModel.voted} человек(а)",
                    textAlign = TextAlign.Center,
                    fontWeight = Bold,
                    color = DGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun AnswerCard(voteAnswer: VoteAnswer) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation( defaultElevation = 12.dp ),
        modifier = Modifier
            .height(45.dp)
            .fillMaxWidth()
            .padding(4.dp)) {
        TextButton(onClick = { /*TODO*/ },
            Modifier
                .background(DLighterBlack)
                .fillMaxWidth()
                .fillMaxHeight()) {
            Text(text = voteAnswer.title, Modifier.padding(start = 8.dp),
                fontWeight = Bold, color = DBlue, textAlign = TextAlign.Start)
        }
    }
}