package com.example.ciclocare.ui.view

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ciclocare.R
import com.example.ciclocare.ui.constants.Formulario
import com.example.ciclocare.ui.theme.PrimaryColor

@Composable
fun LogIn(
    onFormularioChange: (Formulario) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black) // Fondo negro
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Log in",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White // texto blanco sobre fondo negro
        )

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo"
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario", color = Color.White) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                disabledContainerColor = Color.DarkGray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", color = Color.White) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                disabledContainerColor = Color.DarkGray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                errorMessage = null

                if (username == "" && password == "") {
                    onFormularioChange(
                        Formulario(
                            nombre = "Paquita",
                            apellidos = "García Ibáñez",
                            dni = "12345678A",
                            peso = "106",
                            altura = "1,70",
                            fechaNacimiento = "1990-01-01",
                        )
                    )
                    navController.navigate("pantallaPrincipal")
                } else if (username == "admin" && password == "admin") {
                    navController.navigate("pantallaPrincipal")
                } else {
                    errorMessage = "Usuario o contraseña incorrectos"
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
