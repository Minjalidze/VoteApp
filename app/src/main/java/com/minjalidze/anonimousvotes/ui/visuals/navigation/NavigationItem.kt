package com.minjalidze.anonimousvotes.ui.visuals.navigation

import com.minjalidze.anonimousvotes.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Главная")
    object History : NavigationItem("history", R.drawable.ic_history, "История")
    object Settings : NavigationItem("settings", R.drawable.ic_settings, "Настройки")
}
