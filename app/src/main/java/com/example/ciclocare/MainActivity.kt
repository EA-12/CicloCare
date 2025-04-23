 package com.example.ciclocare

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ciclocare.ui.components.BottomNavBar
import com.example.ciclocare.ui.constants.NavItemList
import com.example.ciclocare.ui.theme.CicloCareTheme
import com.example.ciclocare.ui.view.Consejos
import com.example.ciclocare.ui.view.Cuestionarios
import com.example.ciclocare.ui.view.Home
import com.example.ciclocare.ui.view.LogIn
import com.example.ciclocare.ui.view.Monitorizacion
import com.example.ciclocare.ui.view.Perfil
import com.example.ciclocare.ui.view.Registro

 class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CicloCareTheme {
                App()
                // BottomNavScreen()
            }
        }
    }
}

@Composable
fun App() {
    /*
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
     composable("home") { Home(navController) }
     composable("cuestionarios") { Cuestionarios(navController) }
    }
    */
    val screen = 1
    if (screen == 0) {
        LogIn()
    }
    if (screen == 1) {
        Registro()
    }
    if (screen == 2) {
        Perfil()
    }
    if (screen == 3) {
        BottomNavScreen()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavScreen () {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                navItemList = NavItemList.navItemList,
                selectedIndex = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
    ) {
        ContentScreen(selectedIndex)
    }
}

@Composable
fun ContentScreen (selectedIndex: Int) {
    when(selectedIndex) {
        0 -> Home()
        1 -> Cuestionarios()
        2 -> Monitorizacion()
        3 -> Consejos()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CicloCareTheme {
        App()
    }
}