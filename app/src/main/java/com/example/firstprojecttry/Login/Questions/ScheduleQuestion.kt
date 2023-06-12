package com.example.firstprojecttry.Login.Questions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.firstprojecttry.Logic.Schedule
import com.example.firstprojecttry.ScheduleGrid

@Composable
fun ScheduleQuestion(
    state : MutableState<HashMap<String, MutableState<Boolean>>>
) {
    for (i in Schedule.keyNames) {
        state.value[i] = rememberSaveable{ mutableStateOf(false) }
    }
    ScheduleGrid(state = state, true)
}