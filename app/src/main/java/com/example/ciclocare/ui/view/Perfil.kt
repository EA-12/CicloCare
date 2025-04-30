package com.example.ciclocare.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ciclocare.R
import androidx.navigation.NavController
import com.example.ciclocare.ui.constants.Formulario
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun Perfil(
    formularioInicial: Formulario, // <- Recibes los datos desde fuera
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var enEdicion by remember { mutableStateOf(false) }

    // 👇 Copia local editable (estado interno de esta pantalla)
    var formulario by remember { mutableStateOf(formularioInicial) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Imagen
        Image(
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (enEdicion) {
            OutlinedTextField(value = formulario.nombre, onValueChange = { formulario = formulario.copy(nombre = it) }, label = { Text("Nombre") })
            OutlinedTextField(value = formulario.apellidos, onValueChange = { formulario = formulario.copy(apellidos = it) }, label = { Text("Apellidos") })
            OutlinedTextField(value = formulario.fechaNacimiento, onValueChange = { formulario = formulario.copy(fechaNacimiento = it) }, label = { Text("Fecha de nacimiento") })
            OutlinedTextField(value = formulario.dni, onValueChange = { formulario = formulario.copy(dni = it) }, label = { Text("DNI") })
            OutlinedTextField(value = formulario.peso, onValueChange = { formulario = formulario.copy(peso = it) }, label = { Text("Peso") })
            OutlinedTextField(value = formulario.altura, onValueChange = { formulario = formulario.copy(altura = it) }, label = { Text("Altura") })
        } else {
            Text("Nombre: ${formulario.nombre}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Apellidos: ${formulario.apellidos}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text("Fecha de nacimiento: ${formulario.fechaNacimiento}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text("DNI: ${formulario.dni}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text("Peso: ${formulario.peso}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text("Altura: ${formulario.altura}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            enEdicion = !enEdicion
            // Aquí puedes guardar a base de datos o enviar al ViewModel si es necesario
        }) {
            Text(if (enEdicion) "Guardar" else "Editar Perfil")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}
