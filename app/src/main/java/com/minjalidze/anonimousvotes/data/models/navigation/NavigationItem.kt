package com.minjalidze.anonimousvotes.data.models.navigation

import com.minjalidze.anonimousvotes.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    data object Home : NavigationItem("home", R.drawable.ic_home, "Главная")
    data object History : NavigationItem("history", R.drawable.ic_history, "История")
    data object Settings : NavigationItem("settings", R.drawable.ic_settings, "Настройки")
}
