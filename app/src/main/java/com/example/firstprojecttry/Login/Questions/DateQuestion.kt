package com.example.firstprojecttry.Login.Questions

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Logic.UtilityClass.descriptionMap
import com.example.firstprojecttry.Logic.UtilityClass.descriptionStates
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun DateQuestion(
    name: String,
    onError: (Boolean) -> Unit = {}
){


    val field = descriptionMap[name]!!
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        // Blocks Sunday and Saturday from being selected.
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val currentMillis = System.currentTimeMillis()

                // Convert milliseconds to LocalDateTime
                val currentTime = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(currentMillis),
                    ZoneOffset.UTC
                )



                // Subtract 18 years from the current time
                val result = currentTime.minusYears(18)


                // Convert LocalDateTime back to milliseconds
                val resultMillis = result.toInstant(ZoneOffset.UTC).toEpochMilli()
                return resultMillis >= utcTimeMillis
            }
            else {
                return true
            }
        }
    },
        initialDisplayMode = DisplayMode.Input
    )
    val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
    onError(confirmEnabled.value)

    DatePicker(title = {
        Text( style = MaterialTheme.typography.headlineMedium,
            text = field.title,
            fontWeight = FontWeight.Bold)
    },
        headline = { Text( text = field.body,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Gray) },
        state = datePickerState,showModeToggle = false,
        modifier = Modifier.padding(bottom = 40.dp))
    if (datePickerState.selectedDateMillis != null) {
        descriptionStates[name] = datePickerState.selectedDateMillis
    }
}
