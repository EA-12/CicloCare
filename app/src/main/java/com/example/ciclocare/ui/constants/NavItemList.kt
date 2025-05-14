package com.example.ciclocare.ui.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info

object NavItemList {
    val navItemList = listOf(
        NavItem("Inicio", Icons.Default.Home),
        NavItem("Formulario", Icons.Default.DateRange),
        NavItem("Sensor", Icons.Default.Favorite),
        NavItem("Consejos", Icons.Default.Info),
    )
}