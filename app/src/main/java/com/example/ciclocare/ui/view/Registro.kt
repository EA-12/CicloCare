package com.example.ciclocare.ui.view

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.ciclocare.R
import com.example.ciclocare.ui.theme.PrimaryColor
import java.util.Calendar
import com.example.ciclocare.ui.constants.FormularioPrefs
import com.example.ciclocare.ui.constants.UsuarioActual
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.navigation.NavController
@Composable
fun Registro (
    //formulario: Formulario,
    navController: NavController,
    modifier: Modifier = Modifier
){
//: Formulario {

    val context = LocalContext.current
//    var calendario = Calendar.getInstance()
//    val anio = calendario.get(Calendar.YEAR)
//    val mes = calendario.get(Calendar.MONTH)
//    val dia = calendario.get(Calendar.DAY_OF_MONTH)
//
//    var nombre by remember { mutableStateOf("") }
//    var apellidos by remember { mutableStateOf("") }
//    var dni by remember { mutableStateOf("") }
//    var peso by remember { mutableStateOf("") }
//    var altura by remember { mutableStateOf("") }
//    var fechaNacimiento by remember { mutableStateOf("") }
    var formulario by remember { mutableStateOf(UsuarioActual.formulario) }
    var mostrarDatePicker by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    if (mostrarDatePicker) {
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

        val anioInicial = calendarioInicial.get(Calendar.YEAR)
        val mesInicial = calendarioInicial.get(Calendar.MONTH)
        val diaInicial = calendarioInicial.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            context,
            { _, y, m, d ->
                val fecha = "%02d/%02d/%04d".format(d, m + 1, y)
                formulario.fechaNacimiento = fecha
                mostrarDatePicker = false
            },
            anioInicial, mesInicial, diaInicial
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
        Text(text = "Inserte los datos del usuario:", modifier = modifier)
        Spacer(modifier = modifier.padding(top = 16.dp))
        OutlinedTextField(
            value = formulario.nombre,
            onValueChange = { formulario = formulario.copy(nombre = it) },
//            onValueChange = {
//                nombre = it
//                formulario.nombre = it
//            },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(modifier = modifier.padding(top = 16.dp))
        OutlinedTextField(
            value = formulario.apellidos,
            onValueChange = { formulario = formulario.copy(apellidos = it) },
//            onValueChange = {
//                apellidos = it
//                formulario.apellidos = it
//            },
            label = { Text("Apellidos") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(modifier = modifier.padding(top = 16.dp))
        OutlinedTextField(
            value = formulario.dni,
            onValueChange = { formulario = formulario.copy(dni = it) },
//            onValueChange = {
//                dni = it
//                formulario.dni = it
//            },
            label = { Text("DNI") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(modifier = modifier.padding(top = 16.dp))
        OutlinedTextField(
            value = formulario.peso,
            onValueChange = { formulario = formulario.copy(peso = it) },
//            onValueChange = {
//                peso = it
//                formulario.peso = it
//            },
            label = { Text("Peso") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(modifier = modifier.padding(top = 16.dp))
        OutlinedTextField(
            value = formulario.altura,
            onValueChange = { formulario = formulario.copy(altura = it) },
//            onValueChange = {
//                altura = it
//                formulario.altura = it
//            },
            label = { Text("Altura") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(modifier = modifier.padding(top = 16.dp))
        OutlinedTextField(
            value = formulario.fechaNacimiento,
            onValueChange = { formulario = formulario.copy(fechaNacimiento = it) },
//            onValueChange = {
//                fechaNacimiento = it
//                formulario.fechaNacimiento = it
//            },
            modifier = Modifier
                .clickable { mostrarDatePicker = true },
            label = { Text("Fecha de nacimiento") },
            readOnly = true,
            trailingIcon = {
                IconButton(
                    onClick = { mostrarDatePicker = !mostrarDatePicker }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha"
                    )
                }
            }
        )
        Spacer(modifier = modifier.padding(top = 24.dp))
        Button(
            onClick = {
                UsuarioActual.formulario = formulario
                CoroutineScope(Dispatchers.IO).launch {
                    FormularioPrefs.guardarFormulario(context, formulario)
                }
                navController.navigate("pantallaPrincipal")
        },
            colors = ButtonDefaults.run {
                buttonColors(
                    containerColor = PrimaryColor,
                    contentColor = Color.White
                )
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.height(50.dp).width(200.dp)
        ) {
            Text("Enviar")
        }
        Spacer(modifier = modifier.padding(top = 80.dp))
    }
}