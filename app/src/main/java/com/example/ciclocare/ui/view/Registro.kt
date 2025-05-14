package com.example.ciclocare.ui.view

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ciclocare.R
import com.example.ciclocare.ui.constants.FormularioPrefs
import com.example.ciclocare.ui.constants.UsuarioActual
import com.example.ciclocare.ui.theme.PrimaryColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Registro(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var formulario by remember { mutableStateOf(UsuarioActual.formulario) }
    var mostrarDatePickerUltimoPeriodo by remember { mutableStateOf(false) }
    var mostrarErrorGeneral by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    if (mostrarDatePickerUltimoPeriodo) {
        val calendario = Calendar.getInstance()

        DatePickerDialog(
            context,
            { _, y, m, d ->
                val fecha = "%02d/%02d/%04d".format(d, m + 1, y)
                formulario = formulario.copy(ultimoPeriodo = fecha)
                mostrarDatePickerUltimoPeriodo = false
            },
            calendario.get(Calendar.YEAR),
            calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 48.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo"
        )

        Text(text = "Registro de usuario")
        Spacer(modifier = Modifier.height(16.dp))

        // Campo nombre
        OutlinedTextField(
            value = formulario.nombre,
            onValueChange = { formulario = formulario.copy(nombre = it) },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.email ?: "",
            onValueChange = { formulario = formulario.copy(email = it) },
            label = { Text("Correo electrónico") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.None
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.edad ?: "",
            onValueChange = { formulario = formulario.copy(edad = it) },
            label = { Text("Edad") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.peso,
            onValueChange = { formulario = formulario.copy(peso = it) },
            label = { Text("Peso (kg)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.altura,
            onValueChange = { formulario = formulario.copy(altura = it) },
            label = { Text("Altura (cm)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.contrasena ?: "",
            onValueChange = { formulario = formulario.copy(contrasena = it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.ultimoPeriodo ?: "",
            onValueChange = {},
            modifier = Modifier.clickable { mostrarDatePickerUltimoPeriodo = true },
            label = { Text("Último período") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { mostrarDatePickerUltimoPeriodo = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (formulario.nombre.isBlank() ||
                    formulario.email.isNullOrBlank() ||
                    formulario.contrasena.isNullOrBlank() ||
                    formulario.ultimoPeriodo.isNullOrBlank()) {
                    mostrarErrorGeneral = true
                } else {
                    mostrarErrorGeneral = false
                    UsuarioActual.formulario = formulario
                    CoroutineScope(Dispatchers.IO).launch {
                        FormularioPrefs.guardarFormulario(context, formulario)
                    }
                    navController.navigate("pantallaPrincipal")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
        ) {
            Text("Enviar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (mostrarErrorGeneral) {
            Text(
                text = "Por favor, complete los campos obligatorios: Nombre, Correo, Contraseña y Último período.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 30.dp)
            )
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}
