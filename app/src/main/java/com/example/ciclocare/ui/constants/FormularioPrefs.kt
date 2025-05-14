package com.example.ciclocare.ui.constants

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "formulario_prefs")

object FormularioPrefs {
    private val NOMBRE = stringPreferencesKey("nombre")
    private val APELLIDOS = stringPreferencesKey("apellidos")
    private val FECHA_NACIMIENTO = stringPreferencesKey("fecha_nacimiento")
    private val DNI = stringPreferencesKey("dni")
    private val PESO = stringPreferencesKey("peso")
    private val ALTURA = stringPreferencesKey("altura")
    private val CONTRASENA = stringPreferencesKey("contrasena")

    suspend fun guardarFormulario(context: Context, formulario: Formulario) {
        context.dataStore.edit { prefs ->
            prefs[NOMBRE] = formulario.nombre
            prefs[APELLIDOS] = formulario.apellidos
            prefs[FECHA_NACIMIENTO] = formulario.fechaNacimiento
            prefs[PESO] = formulario.peso
            prefs[ALTURA] = formulario.altura
            prefs[CONTRASENA] = formulario.contrasena
        }
    }

    suspend fun cargarFormulario(context: Context): Formulario {
        val prefs = context.dataStore.data.first()
        return Formulario(
            nombre = prefs[NOMBRE] ?: "",
            apellidos = prefs[APELLIDOS] ?: "",
            fechaNacimiento = prefs[FECHA_NACIMIENTO] ?: "",
            peso = prefs[PESO] ?: "",
            altura = prefs[ALTURA] ?: "",
            contrasena = prefs[CONTRASENA] ?: ""

        )
    }

}