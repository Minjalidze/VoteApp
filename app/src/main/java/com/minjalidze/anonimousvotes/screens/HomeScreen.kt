package com.minjalidze.anonimousvotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minjalidze.anonimousvotes.elements.ModalScreen
import com.minjalidze.anonimousvotes.ui.theme.DDarkBlack
import com.minjalidze.anonimousvotes.ui.theme.DGreen

@Composable
fun HomeScreen() {
    var showModal by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DDarkBlack)
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        IconButton(
            onClick = { showModal = true },
            modifier = Modifier
                .padding(bottom = 4.dp, end = 4.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "add vote",
                tint = DGreen,
                modifier = Modifier.size(80.dp)
            )
        }
    }
    if (showModal) {
        ModalScreen {
            showModal = false
        }
    }
}