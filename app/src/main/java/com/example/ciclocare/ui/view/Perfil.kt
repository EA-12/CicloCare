package com.example.ciclocare.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ciclocare.R
import com.example.ciclocare.ui.constants.Formulario
import com.example.ciclocare.ui.theme.PrimaryColor
import androidx.navigation.NavController

@Composable
fun Perfil(
    formularioInicial: Formulario,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var enEdicion by remember { mutableStateOf(false) }
    var formulario by remember { mutableStateOf(formularioInicial) }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color(0xFFD8EFFE) // Fondo pastel azul

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, PrimaryColor, CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (enEdicion) {
                        OutlinedTextField(
                            value = formulario.nombre,
                            onValueChange = { formulario = formulario.copy(nombre = it) },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.apellidos,
                            onValueChange = { formulario = formulario.copy(apellidos = it) },
                            label = { Text("Apellidos") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.fechaNacimiento,
                            onValueChange = { formulario = formulario.copy(fechaNacimiento = it) },
                            label = { Text("Fecha de nacimiento") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.dni,
                            onValueChange = { formulario = formulario.copy(dni = it) },
                            label = { Text("DNI") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.peso,
                            onValueChange = { formulario = formulario.copy(peso = it) },
                            label = { Text("Peso") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.altura,
                            onValueChange = { formulario = formulario.copy(altura = it) },
                            label = { Text("Altura") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Text(
                            text = "Nombre: ${formulario.nombre}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor
                        )
                        Text("Apellidos: ${formulario.apellidos}")
                        Text("Fecha de nacimiento: ${formulario.fechaNacimiento}")
                        Text("DNI: ${formulario.dni}")
                        Text("Peso: ${formulario.peso}")
                        Text("Altura: ${formulario.altura}")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    enEdicion = !enEdicion
                },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = if (enEdicion) "Guardar cambios" else "Editar perfil")
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.popBackStack() },
                border = ButtonDefaults.outlinedButtonBorder,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Volver")
            }
        }
    }
}
