package com.minjalidze.anonimousvotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.minjalidze.anonimousvotes.elements.TopApplicationBar
import com.minjalidze.anonimousvotes.navigation.BottomNavigationBar
import com.minjalidze.anonimousvotes.navigation.NavigationItem
import com.minjalidze.anonimousvotes.screens.HistoryScreen
import com.minjalidze.anonimousvotes.screens.HomeScreen
import com.minjalidze.anonimousvotes.screens.SettingsScreen
import com.minjalidze.anonimousvotes.ui.theme.AnonimousVotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnonimousVotesTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopApplicationBar()
            },
            bottomBar = {
                BottomNavigationBar(navController)
            },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigate(navController = navController)
                }
            })
    }

    @Composable
    fun Navigate(navController: NavHostController) {
        NavHost(navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                HomeScreen()
            }
            composable(NavigationItem.Settings.route) {
                SettingsScreen()
            }
            composable(NavigationItem.History.route) {
                HistoryScreen()
            }
        }
    }
}