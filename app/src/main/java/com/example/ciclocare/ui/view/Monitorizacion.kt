package com.example.ciclocare.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.runtime.remember
import androidx.compose.material3.Tab
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.TabRow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ciclocare.ui.theme.PrimaryColor
import com.example.ciclocare.R

val robotoMono = FontFamily(
    Font(R.font.roboto_mono_regular),
    Font(R.font.roboto_mono_bold, FontWeight.Bold)
)

@Composable
fun Monitorizacion (
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Título
        Text(
            text = "Monitorización",
            modifier = Modifier.padding(top = 50.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.padding(8.dp))

        // Instrucciones
        Text(
            text = "Instrucciones para comenzar:",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = PrimaryColor,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "1. Active el Bluetooth de su dispositivo.",
            modifier = Modifier.padding(vertical = 0.dp),
            textAlign = TextAlign.Center,
            //color = PrimaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Text(
            text = "2. Encienda el sensor.",
            modifier = Modifier.padding(vertical = 0.dp),
            textAlign = TextAlign.Center,
            //color = PrimaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp)
        Text(
            text = "3. Una vez activado, el sensor medirá continuamente sus niveles hormonales.",
            modifier = Modifier.padding(vertical = 0.dp),
            textAlign = TextAlign.Center,
            //color = PrimaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp)

        Spacer(modifier = Modifier.height(20.dp))

        HormoneTabs()
    }
}


@Composable
fun HormoneTabs() {
    val tabs = listOf("LH", "FSH", "Estrógenos", "Progesterona")
    var selectedTabIndex by remember { mutableStateOf(0) }

    TabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = { Text(title) }
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    when (selectedTabIndex) {
        0 -> {
            Text(text = "17 mUI/mL", fontSize = 25.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Image(painter = painterResource(id = R.drawable.img_lh), contentDescription = null)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "La LH suele alcanzar su punto máximo justo antes de la ovulación, entre los días 12 y 16 del ciclo.",
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        1 -> {
            Text(text = "4 mUI/mL", fontSize = 25.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Image(painter = painterResource(id = R.drawable.img_fsh), contentDescription = null)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "La FSH estimula el crecimiento de los folículos. Sus niveles son más altos al inicio del ciclo (días 1-5).",
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        2 -> {
            Text(text = "56.3 pg/mL", fontSize = 25.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Image(painter = painterResource(id = R.drawable.img_estrog), contentDescription = null)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Los estrógenos aumentan durante la fase folicular y alcanzan su pico justo antes de la ovulación.",
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        3 -> {
            Text(text = "0.88 ng/mL", fontSize = 25.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Image(painter = painterResource(id = R.drawable.img_progest), contentDescription = null)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "La progesterona sube tras la ovulación y es clave en la fase lútea (días 15-28).",
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
