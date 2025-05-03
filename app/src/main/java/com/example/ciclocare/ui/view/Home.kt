package com.example.ciclocare.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ciclocare.R
import com.example.ciclocare.ui.components.Aviso
import com.example.ciclocare.ui.components.Calendario
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
            .padding( top = 48.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Perfil",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(2.dp, PrimaryColor, CircleShape)
                    .clickable { navController.navigate("perfil") }
                    .align(Alignment.TopEnd)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo"
        )
        Text(
            text = "¡Bienvenida a CicloCare, ${UsuarioActual.formulario.nombre.replaceFirstChar { it.uppercase() }}!",
            modifier = modifier.padding(top = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text="Tu próximo ciclo comienza en 7 días",
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Aviso(
            texto = "¡No olvides rellenar el cuestionario de hoy!",
            textoBoton = "Rellenar",
            onClickFn = { navController.navigate("cuestionarios") },
        )

        /*
        // Descomentar para mostrar el calendario como imagen
        Image(
            painter = painterResource(id = R.drawable.calendario),
            contentDescription = "Calendario",
            modifier = Modifier
                .width(500.dp)
                .height(300.dp)
        )
        */

        Calendario(modifier = Modifier.padding(20.dp))

        Spacer(modifier = Modifier.height(120.dp))
    }
}