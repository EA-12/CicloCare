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
            text = "1. Active el Bluetooth de su dispositivo.",
            modifier = Modifier.padding(vertical = 0.dp),
            textAlign = TextAlign.Center,
            color = PrimaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Text(
            text = "2. Encienda el sensor.",
            modifier = Modifier.padding(vertical = 0.dp),
            textAlign = TextAlign.Center,
            color = PrimaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp)
        Text(
            text = "3. Una vez activado, el sensor medirá continuamente sus niveles hormonales.",
            modifier = Modifier.padding(vertical = 0.dp),
            textAlign = TextAlign.Center,
            color = PrimaryColor,
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
        //TENEMOS QUE METER LAS GRÁFICAS DE CADA UNO
        0 ->{
            Text(
                text = "17 mUI/mL",
                fontSize = 25.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.img_lh),
                contentDescription = "App logo"
            )
        }
        1 ->{
            Text(
                text = "4 mUI/mL",
                fontSize = 25.sp,
            )
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.img_fsh),
                contentDescription = "App logo"
            )
        }
        2 -> {
            Text(
                text = "56.3 pg/mL",
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.img_estrog),
                contentDescription = "App logo"
            )
        }
        3 -> {
            Text(
                text = "0.88 ng/mL",
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.img_progest),
                contentDescription = "App logo"
            )
        }
    }
}
