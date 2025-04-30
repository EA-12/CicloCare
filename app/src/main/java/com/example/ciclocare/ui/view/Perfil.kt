package com.example.ciclocare.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ciclocare.ui.constants.Formulario

@Composable
fun Perfil (
    formulario: Formulario,
    navController: NavController,
    modifier: Modifier = Modifier
): Formulario {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Perfil", modifier = modifier)
        Text(text= "Nombre: ${formulario.nombre}", modifier = modifier)
    }
    return formulario
}