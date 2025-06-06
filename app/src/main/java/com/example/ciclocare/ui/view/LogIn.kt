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
fun LogIn(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo"
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
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
        Button(
            onClick = {
                errorMessage = null
                if (email.isBlank() || password.isBlank()) {
                    errorMessage = "Por favor, introduzca correo y contraseña"
                    return@Button
                }

                CoroutineScope(Dispatchers.IO).launch {
                    val formulario = FormularioPrefs.cargarFormulario(context)

                    if (formulario.email == email && formulario.contrasena == password) {
                        UsuarioActual.formulario = formulario
                        withContext(Dispatchers.Main) {
                            navController.navigate("pantallaPrincipal")
                        }
                    } else if (email == "admin" && password == "admin") {
                        UsuarioActual.formulario = Formulario(
                            email = "admin",
                            edad = "-",
                            peso = "-",
                            altura = "-",
                            contrasena = "admin",
                            ultimoPeriodo = "-"
                        )
                        withContext(Dispatchers.Main) {
                            navController.navigate("pantallaPrincipal")
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            errorMessage = "Correo o contraseña incorrectos"
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
