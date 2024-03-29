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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.minjalidze.anonimousvotes.data.api.API
import com.minjalidze.anonimousvotes.data.models.Authorization
import com.minjalidze.anonimousvotes.data.models.navigation.NavigationItem
import com.minjalidze.anonimousvotes.filesystem.INI
import com.minjalidze.anonimousvotes.service.APIService
import com.minjalidze.anonimousvotes.ui.theme.AnonimousVotesTheme
import com.minjalidze.anonimousvotes.ui.visuals.elements.ShowDialog
import com.minjalidze.anonimousvotes.ui.visuals.navigation.BottomNavigationBar
import com.minjalidze.anonimousvotes.ui.visuals.screens.HistoryScreen
import com.minjalidze.anonimousvotes.ui.visuals.screens.HomeScreen
import com.minjalidze.anonimousvotes.ui.visuals.screens.SettingsScreen
import com.minjalidze.anonimousvotes.ui.visuals.topbar.TopApplicationBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    companion object {
        var settingsINI: INI = INI("")
    }
    private var _currentScreen : String = ""

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            API.initialize()
        }

        val path = getExternalFilesDir(null)

        settingsINI = INI("$path/settings.ini")
        if (!settingsINI.load()) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://vote-mobile-api.whywelive.me")
                .build()
            val service = retrofit.create(APIService::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val response = service.register()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val prettyJson = gson.toJson(
                            JsonParser.parseString( response.body() ?.string() )
                        )
                        val authToken: Authorization = Json.decodeFromString(prettyJson)
                        settingsINI.set("authToken", authToken.token)
                        settingsINI.set("selectedGradient", "0")
                        settingsINI.store()
                    }
                }
            }
        }
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
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
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
        val uri = "app://com.minjalidze.anonimousvotes"

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
                route = "vote?id={id}",
                deepLinks = listOf(navDeepLink { uriPattern = "$uri/{id}" })
            ) { backStackEntry ->
                HomeScreen(backStackEntry.arguments?.getString("id")?.toInt() ?: 0, true)
                _currentScreen = NavigationItem.Home.route
            }
        }
        navController.handleDeepLink(intent)
    }
}