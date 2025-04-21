package com.example.ciclocare.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.ciclocare.ui.constants.NavItem
import com.example.ciclocare.ui.theme.PrimaryColor
import com.example.ciclocare.ui.theme.White

@Composable
fun BottomNavBar(
    navItemList: List<NavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
) {
    NavigationBar (
        containerColor = PrimaryColor,
    ){
        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    onItemSelected(index)
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = "Icon")
                },
                label = {
                    Text(text = navItem.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = White,
                    unselectedIconColor = Color.White.copy(alpha = 0.7f),
                    selectedTextColor = White,
                    unselectedTextColor = Color.White.copy(alpha = 0.7f),
                    indicatorColor = Color.Transparent,
                )
            )
        }
    }
}