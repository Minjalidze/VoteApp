package com.minjalidze.anonimousvotes.items

import com.minjalidze.anonimousvotes.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Главная")
    object Quiz : NavigationItem("quiz", R.drawable.ic_quiz, "Опросы")
    object History : NavigationItem("history", R.drawable.ic_history, "История")
    object Settings : NavigationItem("settings", R.drawable.ic_settings, "Настройки")
}