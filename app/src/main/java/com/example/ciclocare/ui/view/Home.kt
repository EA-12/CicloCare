package com.example.ciclocare.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ciclocare.R
import com.example.ciclocare.ui.constants.Formulario
import com.example.ciclocare.ui.theme.PrimaryColor

@Composable
fun Home (
    formulario: Formulario,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding( top = 48.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo"
        )
        Text(
            text = "¡Bienvenida a CicloCare, ${formulario.nombre}!",
            modifier = modifier.padding(top = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Button(
            onClick = { navController.navigate("perfil") },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            modifier = modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Perfil (botón cutre, cambiar)",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
    //AQUÍ HAY QUE METER EL PERFIL
}