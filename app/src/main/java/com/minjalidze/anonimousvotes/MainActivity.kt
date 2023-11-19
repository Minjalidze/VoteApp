package com.minjalidze.anonimousvotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.minjalidze.anonimousvotes.elements.TopBar
import com.minjalidze.anonimousvotes.ui.theme.AnonimousVotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnonimousVotesTheme {

                Scaffold(
                    topBar = {
                        TopBar.MakeBar()
                    },
                ) {
                    Box(modifier = Modifier.fillMaxSize().padding(it)) {

                    }
                }
            }
        }
    }
}