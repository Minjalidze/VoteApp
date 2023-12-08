package com.minjalidze.anonimousvotes.ui.visuals.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.minjalidze.anonimousvotes.data.api.API
import com.minjalidze.anonimousvotes.ui.theme.DDarkBlack
import com.minjalidze.anonimousvotes.ui.theme.DGray
import com.minjalidze.anonimousvotes.ui.visuals.elements.VoteModelCard
import com.minjalidze.anonimousvotes.ui.visuals.elements.rememberQrBitmapPainter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    DelicateCoroutinesApi::class
)
@Composable
fun HomeScreen(voteID: Int = -1, fromLink: Boolean = false) {
    val votes = API.getVotes()

    val sheetState = rememberModalBottomSheetState()

    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var currentVoteID by remember { mutableIntStateOf(voteID) }
    var refreshing by remember { mutableStateOf(false) }

    var linkOpened by remember { mutableStateOf(false) }

    fun refresh() = GlobalScope.launch {
        refreshing = true
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
                        VoteModelCard(it) {
                            currentVoteID = it.id
                            isSheetOpen = true
                        }
                    }
                }
            }
            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
        }
    }

    if (fromLink && !linkOpened) {
        isSheetOpen = true

        linkOpened = true
        currentVoteID = voteID
    }

    if (isSheetOpen) {
        val vote = API.selectVote(currentVoteID)
        ModalBottomSheet(onDismissRequest = { isSheetOpen = false }, sheetState = sheetState, containerColor = DGray) {
            Column (modifier = Modifier
                .padding(8.dp)
                .align(CenterHorizontally)) {
                Image(
                    bitmap = rememberQrBitmapPainter(content = "app://com.minjalidze.anonymousvotes/vote/$currentVoteID").asImageBitmap(),
                    contentDescription = "test qr code",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.background(Color.Gray).align(CenterHorizontally)
                )
                Divider(modifier = Modifier.padding(8.dp))
                Text(text = vote!!.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(CenterHorizontally))
            }
        }
    }
}