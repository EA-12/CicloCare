package com.example.ciclocare.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ciclocare.ui.theme.PrimaryColor

data class Consejo(
    val titulo: String,
    val descripcion: String
)

@Composable
fun Consejos(
    modifier: Modifier = Modifier
) {
    val listaConsejos = listOf(
        Consejo(
            "¿Senos sensibles?",
            "Aplica compresas frías o tibias en la zona y usa ropa interior cómoda. Reducir el consumo de sal puede ayudar a disminuir la retención de líquidos."
        ),
        Consejo(
            "¿Cólicos menstruales?",
            "Practicar ejercicio ligero como yoga o caminar puede aliviar el dolor. También es útil aplicar calor local y mantener una dieta antiinflamatoria."
        ),
        Consejo(
            "Alimentos para tu salud hormonal",
            "Prioriza verduras de hoja verde, frutas bajas en azúcar, omega 3 y granos integrales. Evita azúcares añadidos y grasas trans."
        ),
        Consejo(
            "Suavizar síntomas del ciclo",
            "Mantén una rutina regular de sueño, reduce el estrés con respiración profunda y evita cafeína en días previos al ciclo."
        ),
        Consejo(
            "¿Poca energía o fatiga?",
            "Asegura un desayuno equilibrado, con proteína y grasa saludable. Evita los picos de glucosa controlando los carbohidratos refinados."
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Consejos adaptados a tu ciclo",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(listaConsejos) { consejo ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF0F3)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = consejo.titulo,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = consejo.descripcion,
                            fontSize = 14.sp,
                            color = Color(0xFF444444),
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }

            // ✅ Aquí está corregido el Spacer
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

    }
}






