package com.minjalidze.anonimousvotes.ui.visuals.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.minjalidze.anonimousvotes.data.api.API
import com.minjalidze.anonimousvotes.data.api.API.Companion.getVotes
import com.minjalidze.anonimousvotes.ui.theme.DDarkBlack
import com.minjalidze.anonimousvotes.ui.visuals.elements.VoteModelCard
import com.minjalidze.anonimousvotes.ui.visuals.elements.rememberQrBitmapPainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val votes = getVotes()

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        API.initialize()
        refreshing = false
    }
    val state = rememberPullRefreshState(refreshing, ::refresh)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DDarkBlack)
            .wrapContentSize(Alignment.TopCenter)
            .padding(5.dp)
    ) {
        Box(Modifier.pullRefresh(state)) {
            if (!refreshing) {
                LazyColumn {
                    items(votes) {
                        VoteModelCard(it) { isSheetOpen = true }
                    }
                }
            }
            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
        }
    }

    if (isSheetOpen) {
        ModalBottomSheet(onDismissRequest = { isSheetOpen = false }, sheetState = sheetState) {
            Row (modifier = Modifier
                .padding(8.dp).align(CenterHorizontally).background(Color.Transparent),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top) {
                Image(
                    painter = rememberQrBitmapPainter(content = "app://com.minjalidze.anonymousvotes", size = 250.dp, padding = 8.dp),
                    contentDescription = "test qr code",
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}