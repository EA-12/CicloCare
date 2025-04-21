package com.example.ciclocare.ui.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info

object NavItemList {
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Forms", Icons.Default.DateRange),
        NavItem("Monitoring", Icons.Default.Favorite),
        NavItem("Advice", Icons.Default.Info),
    )
}