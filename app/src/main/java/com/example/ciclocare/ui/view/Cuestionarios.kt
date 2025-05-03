package com.example.ciclocare.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ciclocare.ui.theme.PrimaryColor

// ✅ IMPORTS NECESARIOS PARA USAR 'by mutableStateOf'
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

@Composable
fun Cuestionarios(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val opcionesEstadoAnimo = listOf("En calma", "Cambios de humor", "Feliz", "Con energía")
    val opcionesSintomas = listOf("Me encuentro bien", "Cólicos", "Pechos sensibles", "Fatiga", "Acné", "Dolor de espalda", "Dolor de cabeza", "Antojos")
    val opcionesFlujo = listOf("Nada de flujo", "Clara de huevo", "Manchado intermenstrual", "Pegajoso")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Asegura que la pantalla sea desplazable
    ) {
        // Título y checklist de "Estado de ánimo"
        TituloSeccion("Estado de ánimo")
        opcionesEstadoAnimo.forEach { opcion ->
            CheckItem(texto = opcion)
        }

        // Título y checklist de "Síntomas"
        TituloSeccion("Síntomas")
        opcionesSintomas.forEach { opcion ->
            CheckItem(texto = opcion)
        }

        // Título y checklist de "Flujo vaginal"
        TituloSeccion("Flujo vaginal")
        opcionesFlujo.forEach { opcion ->
            CheckItem(texto = opcion)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para enviar
        Button(
            onClick = { navController.navigate("pantallaPrincipal") },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Enviar",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun CheckItem(texto: String) {
    var checked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = texto)
    }
}

@Composable
fun TituloSeccion(titulo: String) {
    Text(
        text = titulo,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 12.dp)
    )
}
