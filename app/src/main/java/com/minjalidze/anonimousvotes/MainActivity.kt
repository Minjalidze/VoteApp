package com.minjalidze.anonimousvotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.minjalidze.anonimousvotes.data.api.API
import com.minjalidze.anonimousvotes.ui.theme.AnonimousVotesTheme
import com.minjalidze.anonimousvotes.ui.visuals.elements.ShowDialog
import com.minjalidze.anonimousvotes.ui.visuals.navigation.BottomNavigationBar
import com.minjalidze.anonimousvotes.ui.visuals.navigation.NavigationItem
import com.minjalidze.anonimousvotes.ui.visuals.screens.HistoryScreen
import com.minjalidze.anonimousvotes.ui.visuals.screens.HomeScreen
import com.minjalidze.anonimousvotes.ui.visuals.screens.SettingsScreen
import com.minjalidze.anonimousvotes.ui.visuals.topbar.TopApplicationBar

class MainActivity : ComponentActivity() {
    private var _currentScreen : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        API.initialize()

        setContent {
            AnonimousVotesTheme {
                MainScreen()
            }
        }
    }

    @Preview
    @Composable
    fun MainScreen() {
        val navController = rememberNavController()

        var showModal by remember { mutableStateOf(false) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route

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
            },
            floatingActionButton = {
                if (currentRoute == NavigationItem.Home.route) {
                    FloatingActionButton(modifier = Modifier
                        .size(40.dp), onClick = { showModal = true }) {
                        Icon(Icons.Rounded.Add, "addVote")
                    }
                }
            })

        if (showModal) {
            ShowDialog(onDismiss = { showModal = false }, onExit = { showModal = false })
        }
    }

    @Composable
    fun Navigate(navController: NavHostController) {
        NavHost(navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                HomeScreen()
                _currentScreen = NavigationItem.Home.route
            }
            composable(NavigationItem.Settings.route) {
                SettingsScreen()
                _currentScreen = NavigationItem.Settings.route
            }
            composable(NavigationItem.History.route) {
                HistoryScreen()
                _currentScreen = NavigationItem.History.route
            }
            composable(
                route = "vote/{deepLinkArg}",
                arguments = listOf(navArgument("deepLinkArg") { type = NavType.StringType })
            ) { backStackEntry ->
                val deepLinkArg = backStackEntry.arguments?.getString("deepLinkArg")
                HomeScreen()
            }
        }
        navController.handleDeepLink(intent)
    }
}