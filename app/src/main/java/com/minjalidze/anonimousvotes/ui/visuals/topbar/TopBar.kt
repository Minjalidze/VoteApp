package com.minjalidze.anonimousvotes.ui.visuals.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopApplicationBar() {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = com.minjalidze.anonimousvotes.ui.theme.DLighterBlack
        ),
        title = {
            Column {
                Text(text = "Anonymous Votes",
                    color = Color.White)
                Text(text = "Сервис анонимных голосований", style = typography.labelSmall,
                    color = Color.White)
            }
        },
        navigationIcon = {
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(Icons.Filled.ArrowBack, "", tint = Color.Black)
//            }
        }
    )
}