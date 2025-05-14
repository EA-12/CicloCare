package com.example.ciclocare.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ciclocare.R
import com.example.ciclocare.ui.constants.Formulario
import com.example.ciclocare.ui.theme.PrimaryColor
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.ciclocare.ui.constants.UsuarioActual
import com.example.ciclocare.ui.constants.FormularioPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Perfil(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var enEdicion by remember { mutableStateOf(false) }
    var formulario by remember { mutableStateOf(UsuarioActual.formulario) }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Perfil",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
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
                            value = formulario.email ?: "",
                            onValueChange = { formulario = formulario.copy(email = it) },
                            label = { Text("Correo electrónico") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.edad ?: "",
                            onValueChange = { formulario = formulario.copy(edad = it) },
                            label = { Text("Edad") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.peso,
                            onValueChange = { formulario = formulario.copy(peso = it) },
                            label = { Text("Peso (kg)") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.altura,
                            onValueChange = { formulario = formulario.copy(altura = it) },
                            label = { Text("Altura (cm)") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.contrasena ?: "",
                            onValueChange = { formulario = formulario.copy(contrasena = it) },
                            label = { Text("Contraseña") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = formulario.ultimoPeriodo ?: "",
                            onValueChange = { formulario = formulario.copy(ultimoPeriodo = it) },
                            label = { Text("Último período (dd/MM/yyyy)") },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true
                        )
                    } else {
                        Text(
                            text = "Nombre: ${formulario.nombre}",
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor
                        )
                        Text("Correo: ${formulario.email ?: "-"}", fontSize = 16.sp)
                        Text("Edad: ${formulario.edad ?: "-"}", fontSize = 16.sp)
                        Text("Peso: ${formulario.peso}", fontSize = 16.sp)
                        Text("Altura: ${formulario.altura}", fontSize = 16.sp)
                        Text("Contraseña: ${formulario.contrasena ?: "-"}", fontSize = 16.sp)
                        Text("Último período: ${formulario.ultimoPeriodo ?: "-"}", fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (enEdicion) {
                        UsuarioActual.formulario = formulario
                        CoroutineScope(Dispatchers.IO).launch {
                            FormularioPrefs.guardarFormulario(context, formulario)
                        }
                    }
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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    UsuarioActual.formulario = Formulario()
                    CoroutineScope(Dispatchers.IO).launch {
                        FormularioPrefs.guardarFormulario(context, Formulario())
                    }
                    navController.navigate("login") {
                        popUpTo("pantallaPrincipal") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Cerrar sesión")
            }

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}
