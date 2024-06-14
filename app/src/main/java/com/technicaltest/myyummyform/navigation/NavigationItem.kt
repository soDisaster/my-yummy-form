package com.technicaltest.myyummyform.navigation

import com.technicaltest.myyummyform.data.enum.Screen

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Form : NavigationItem(Screen.FORM.name)
}