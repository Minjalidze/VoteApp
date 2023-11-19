package com.minjalidze.anonimousvotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.minjalidze.anonimousvotes.elements.NavigationBar.Companion.BottomNavigationBar
import com.minjalidze.anonimousvotes.elements.TopBar.Companion.TopApplicationBar
import com.minjalidze.anonimousvotes.items.NavigationItem
import com.minjalidze.anonimousvotes.ui.theme.AnonimousVotesTheme
import com.minjalidze.anonimousvotes.views.History.Companion.HistoryScreen
import com.minjalidze.anonimousvotes.views.Home.Companion.HomeScreen
import com.minjalidze.anonimousvotes.views.Quiz.Companion.QuizScreen
import com.minjalidze.anonimousvotes.views.Settings.Companion.SettingsScreen

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
            topBar = {
                TopApplicationBar()
            },
            bottomBar = { BottomNavigationBar(navController) },
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
            composable(NavigationItem.Quiz.route) {
                QuizScreen()
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