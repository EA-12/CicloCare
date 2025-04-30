package com.example.ciclocare.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.ciclocare.ui.theme.PrimaryColor
import androidx.compose.runtime.*



@Composable
fun Cuestionarios (
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Definimos las opciones de cada checklist
    val opcionesEstadoAnimo = listOf("En calma", "Cambios de humor", "Feliz", "Con energía")
    val opcionesSintomas = listOf("Me encuentro bien", "Cólicos", "Pechos sensibles", "Fatiga", "Acné", "Dolor de espalda", "Dolor de cabeza", "Antojos")
    val opcionesFlujo = listOf("Nada de flujo", "Clara de huevo", "Manchado intermenstrual", "Pegajoso")

    // Usamos una columna para organizar todos los elementos
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

        // Agregar espacio entre el formulario y el botón
        Spacer(modifier = Modifier.height(24.dp))

        // El botón que navega a la pantalla principal
        Button(
            onClick = { navController.navigate("pantallaPrincipal") },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Enviar",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        // Puedes agregar más espacios si lo deseas
        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun CheckItem(texto: String) {
    // Creamos una variable mutable para gestionar el estado del Checkbox
    var checked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        // Checkbox con el estado mutable
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }, // Cambiamos el estado del checkbox
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
        modifier = Modifier
            .padding(vertical = 12.dp)
    )
}

