package com.example.ciclocare.ui.view

import android.os.Build.VERSION_CODES.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ciclocare.MainActivity
import com.example.ciclocare.R
import com.example.ciclocare.ui.constants.Formulario
import com.example.ciclocare.ui.theme.PrimaryColor
import androidx.compose.ui.platform.LocalContext
import com.example.ciclocare.ui.constants.FormularioPrefs
import com.example.ciclocare.ui.constants.UsuarioActual
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LogIn (
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /*
        Text(
            text = "Log in",
            modifier = modifier,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        */

        Image(
            painter = painterResource(id = com.example.ciclocare.R.drawable.logo),
            contentDescription = "App logo"
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("DNI") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        val context = LocalContext.current
        Button(onClick = {
            errorMessage = null // limpiamos mensaje anterior
            if (username.isBlank() || password.isBlank()) {
                errorMessage = "Por favor, complete el DNI y la contraseña"
                return@Button
            }
            CoroutineScope(Dispatchers.IO).launch {
                val formulario = FormularioPrefs.cargarFormulario(context)

                if (formulario.dni == username && formulario.contrasena == password) {
                    UsuarioActual.formulario = formulario
                    withContext(Dispatchers.Main) {
                        navController.navigate("pantallaPrincipal")
                    }
                } else if (username == "admin" && password == "admin") {
                    // Carga un usuario falso para pruebas
                    UsuarioActual.formulario = Formulario(
                        nombre = "Administrador",
                        apellidos = "App",
                        dni = "admin",
                        peso = "-",
                        altura = "-",
                        fechaNacimiento = "-",
                        contrasena = "admin"
                    )
                    withContext(Dispatchers.Main) {
                        navController.navigate("pantallaPrincipal")
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        errorMessage = "DNI o contraseña incorrectos"
                    }
                }
            }
        },


            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor,
                contentColor = Color.White
            )
        ) {
            Text("Iniciar sesión")
        }


        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 12.dp)
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        // Texto clicable para registrarse
        Text(
            text = "¿No está registrada? Regístrese",
            color = PrimaryColor,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable {
                navController.navigate("registro")
            }
        )
    }
}