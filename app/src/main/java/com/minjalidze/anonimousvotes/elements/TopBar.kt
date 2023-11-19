package com.minjalidze.anonimousvotes.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class TopBar {
    companion object {
        @JvmStatic
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun MakeBar() {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ArrowBack, "", tint = Color.Black)
                    }
                }
            )
        }
    }
}