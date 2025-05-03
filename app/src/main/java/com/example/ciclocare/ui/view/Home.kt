package com.example.ciclocare.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ciclocare.R
import com.example.ciclocare.ui.constants.UsuarioActual
import com.example.ciclocare.ui.theme.PrimaryColor

@Composable
fun Home (
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
            text = "¡Bienvenida a CicloCare, ${UsuarioActual.formulario.nombre}!",
            modifier = modifier.padding(top = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        IconButton(onClick = {  navController.navigate("perfil")  }) {
            Image(
                painter = painterResource(id = R.drawable.icono_perfil), // tu ícono personalizado
                contentDescription = "Perfil",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, PrimaryColor, CircleShape)
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.calendario),
            contentDescription = "Calendario",
            modifier = Modifier
                .width(500.dp)
                .height(300.dp)
        )
        Spacer(modifier = Modifier.height(200.dp))
    }
    //AQUÍ HAY QUE METER EL PERFIL
}