package com.example.ciclocare.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Calendario(modifier : Modifier = Modifier) {
    val daysInMonth = 31
    val startDayOfWeek = 3 // Mayo 2025 empieza en jueves
    val highlightedRange = 14..18

    val weeks = mutableListOf<List<Int?>>()
    var currentDay = 1

    while (currentDay <= daysInMonth) {
        val week = MutableList(7) { index ->
            val dayNumber = (weeks.size * 7 + index) - startDayOfWeek + 1
            if (dayNumber in 1..daysInMonth) dayNumber else null
        }
        weeks.add(week)
        currentDay += 7
    }

    Column(
        modifier = Modifier
            .padding(40.dp)
    ) {
        Text(
            text = "Mayo 2025",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp, start = 14.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("L", "M", "X", "J", "V", "S", "D").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        weeks.forEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {
                week.forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(2.dp)
                            .background(
                                if (day != null && day in highlightedRange)
                                    Color.Red.copy(alpha = 0.2f)
                                else
                                    Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (day != null) {
                            Text(text = day.toString())
                        }
                    }
                }
            }
        }
    }
}