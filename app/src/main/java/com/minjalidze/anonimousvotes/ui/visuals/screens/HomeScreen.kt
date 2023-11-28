package com.minjalidze.anonimousvotes.ui.visuals.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minjalidze.anonimousvotes.data.api.API.Companion.getVotes
import com.minjalidze.anonimousvotes.ui.theme.DDarkBlack
import com.minjalidze.anonimousvotes.ui.visuals.elements.VoteModelCard

@Composable
fun HomeScreen() {
    val votes = getVotes()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DDarkBlack)
            .wrapContentSize(Alignment.TopCenter)
            .padding(5.dp)
    ) {
        LazyColumn {
            items(votes) {
                VoteModelCard(it)
            }
        }
    }
}