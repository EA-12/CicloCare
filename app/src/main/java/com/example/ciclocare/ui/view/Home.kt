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
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home (
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 48.dp)
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

        //  CALCULAR PRÓXIMO PERIODO
        val formatoFecha = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val proximoPeriodo = try {
            UsuarioActual.formulario.ultimoPeriodo?.let {
                val fechaUltimo = LocalDate.parse(it, formatoFecha)
                fechaUltimo.plusDays(28).format(formatoFecha)
            } ?: "-"
        } catch (e: Exception) {
            "-"
        }

        Text(
            text = "Tu próximo ciclo comienza el $proximoPeriodo",
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        val context = LocalContext.current
        val sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

        val fechaHoy = LocalDate.now().toString()
        val fechaUltimoCuestionario = sharedPref.getString("fecha_cuestionario", "")

        if (fechaHoy != fechaUltimoCuestionario) {
            Aviso(
                texto = "¡No olvides rellenar el cuestionario de hoy!",
                textoBoton = "Rellenar",
                onClickFn = { navController.navigate("cuestionarios") },
            )
        }

        Calendario(modifier = Modifier.padding(20.dp))

        Spacer(modifier = Modifier.height(120.dp))
    }
}
