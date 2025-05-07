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
    var mostrarDatePickerNacimiento by remember { mutableStateOf(false) }
    var mostrarDatePickerUltimoPeriodo by remember { mutableStateOf(false) }
    var mostrarErrorGeneral by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    if (mostrarDatePickerNacimiento) {
        val calendarioInicial = if (formulario.fechaNacimiento.isNotEmpty()) {
            val partes = formulario.fechaNacimiento.split("/")
            Calendar.getInstance().apply {
                set(Calendar.DAY_OF_MONTH, partes[0].toInt())
                set(Calendar.MONTH, partes[1].toInt() - 1)
                set(Calendar.YEAR, partes[2].toInt())
            }
        } else {
            Calendar.getInstance()
        }

        DatePickerDialog(
            context,
            { _, y, m, d ->
                formulario = formulario.copy(fechaNacimiento = "%02d/%02d/%04d".format(d, m + 1, y))
                mostrarDatePickerNacimiento = false
            },
            calendarioInicial.get(Calendar.YEAR),
            calendarioInicial.get(Calendar.MONTH),
            calendarioInicial.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

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

        Text(text = "Inserte los datos del usuario:")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.nombre,
            onValueChange = { formulario = formulario.copy(nombre = it) },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.apellidos,
            onValueChange = { formulario = formulario.copy(apellidos = it) },
            label = { Text("Apellidos") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.dni,
            onValueChange = { formulario = formulario.copy(dni = it) },
            label = { Text("DNI") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.peso,
            onValueChange = { formulario = formulario.copy(peso = it) },
            label = { Text("Peso") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = formulario.altura,
            onValueChange = { formulario = formulario.copy(altura = it) },
            label = { Text("Altura") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fecha de nacimiento
        OutlinedTextField(
            value = formulario.fechaNacimiento,
            onValueChange = {},
            modifier = Modifier.clickable { mostrarDatePickerNacimiento = true },
            label = { Text("Fecha de nacimiento") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { mostrarDatePickerNacimiento = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contraseña
        OutlinedTextField(
            value = formulario.contrasena ?: "",
            onValueChange = { formulario = formulario.copy(contrasena = it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Último período
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
                    formulario.dni.isBlank() ||
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
                text = "Por favor, complete los campos obligatorios: Nombre, DNI, Contraseña y Último período.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 30.dp)
            )

        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}
