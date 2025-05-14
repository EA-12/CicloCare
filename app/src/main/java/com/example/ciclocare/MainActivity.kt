package com.example.ciclocare

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import com.example.ciclocare.ui.constants.Formulario
import com.example.ciclocare.ui.constants.NavItemList
import com.example.ciclocare.ui.theme.CicloCareTheme
import com.example.ciclocare.ui.view.Consejos
import com.example.ciclocare.ui.view.Cuestionarios
import com.example.ciclocare.ui.view.Home
import com.example.ciclocare.ui.view.LogIn
import com.example.ciclocare.ui.view.Monitorizacion
import com.example.ciclocare.ui.view.Perfil
import com.example.ciclocare.ui.view.Registro
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CicloCareTheme {
                App()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LogIn(navController) }
        composable("pantallaPrincipal") { BottomNavScreen(navController) }
        composable("registro") {Registro(navController) }
        composable("perfil") { Perfil(navController) }
        composable("cuestionarios") { Cuestionarios(navController) }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavScreen (navController: NavController) {
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
        ContentScreen(selectedIndex, navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentScreen (selectedIndex: Int, navController: NavController) {
    when(selectedIndex) {
        0 -> Home(navController)
        1 -> Cuestionarios(navController)
        2 -> Monitorizacion()
        3 -> Consejos()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CicloCareTheme {
        App()
    }
}