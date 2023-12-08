package com.minjalidze.anonimousvotes.ui.visuals.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minjalidze.anonimousvotes.MainActivity
import com.minjalidze.anonimousvotes.ui.theme.DDarkBlack
import com.minjalidze.anonimousvotes.ui.theme.DGreen
import com.minjalidze.anonimousvotes.ui.theme.GradientStorage
import com.minjalidze.anonimousvotes.ui.theme.getGradient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DDarkBlack)
            .wrapContentSize(Alignment.TopCenter)
            .padding(5.dp)
    ) {
        GradientsCard()
    }
}

fun changeGradient(id: Int) {
    CoroutineScope(Dispatchers.IO).launch {
        MainActivity.settingsINI.set("selectedGradient", id.toString())
        MainActivity.settingsINI.store()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradientsCard() {
    var selectedGradient by remember { mutableIntStateOf(MainActivity.settingsINI.get("selectedGradient")!!.toInt()) }
    Card(elevation = CardDefaults.cardElevation( defaultElevation = 4.dp ),
        shape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp),
        modifier = Modifier
            .padding(5.dp)
            .height(150.dp)
            .fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxSize()
            .background(Color.Black)) {
            LazyRow(modifier = Modifier.fillMaxWidth(), state = rememberLazyListState()) {
                itemsIndexed(GradientStorage.gradients) { _, item ->
                    Card(
                        onClick = { changeGradient(item.id); selectedGradient = item.id },
                        elevation = CardDefaults.cardElevation( defaultElevation = 8.dp ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(100.dp)
                            .padding(8.dp)
                    ) {
                        val gradient = getGradient(item.id)
                        val horizontalGradientBrush = Brush.horizontalGradient(
                            colors = listOf(
                                gradient.firstColor,
                                gradient.secondColor
                            ), tileMode = TileMode.Mirror
                        )

                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(horizontalGradientBrush)) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .background(color = Color.Black.copy(alpha = 0.75f))
                                    .fillMaxWidth()
                                    .height(70.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(item.title,
                                    style = TextStyle(brush = horizontalGradientBrush, fontSize = 15.sp),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold)
                            }
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(4.dp)
                            ) {
                                if (item.id == selectedGradient) {
                                    Icon(Icons.Filled.CheckCircle, "", tint = DGreen)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}