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

@Composable
fun App() {
    val navController = rememberNavController()
    var formulario by remember { mutableStateOf(Formulario()) }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LogIn(onFormularioChange = { formulario = it }, navController) }
        composable("pantallaPrincipal") { BottomNavScreen(navController, formulario) }
        //composable("registro") { formulario = Registro(formulario, navController) }
        composable("perfil") { Perfil(formulario, navController) }
        composable("cuestionarios") { Cuestionarios(navController) }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavScreen (navController: NavController, formulario: Formulario) {
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
        ContentScreen(selectedIndex, navController, formulario)
    }
}

@Composable
fun ContentScreen (selectedIndex: Int, navController: NavController, formulario: Formulario) {
    when(selectedIndex) {
        0 -> Home(formulario, navController)
        1 -> Cuestionarios(navController)
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