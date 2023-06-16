package com.example.firstprojecttry.Profile.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Logic.Executor
import com.example.firstprojecttry.Logic.Schedule
import com.example.firstprojecttry.Logic.UtilityClass
import com.example.firstprojecttry.R


@Composable
fun AvailabilityBlock(executor : MutableState<Executor>, changeable: Boolean = true) {
    var selectedGrid = rememberSaveable{ mutableStateOf(false) }

    var stateGrid = rememberSaveable{ mutableStateOf(HashMap<String, MutableState<Boolean>>()) }
    for (i in Schedule.keyNames) {
        stateGrid.value[i] = rememberSaveable{ mutableStateOf(executor.value.schedule.scheduleMap[i]!!) }
    }

    ListItem(
        headlineContent = {
            Surface(
                onClick = {
                    if (changeable) {
                        selectedGrid.value = true;
                    }
                }
            ){
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        "Availability",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = "Availability arrow",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }
        },
        supportingContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ScheduleGrid(stateGrid)
            }
        }
    )
    if (selectedGrid.value) {
        var state = rememberSaveable{ mutableStateOf(HashMap<String, MutableState<Boolean>>()) }
        for (i in Schedule.keyNames) {
            state.value[i] = rememberSaveable{ mutableStateOf(stateGrid.value[i]!!.value) }
        }
        GridChangeSheet(state, selectedGrid, onClickSave = {
            var toex : MutableMap<String, Boolean> = HashMap();
            for (i in Schedule.keyNames) {
                stateGrid.value[i]!!.value = state.value[i]!!.value
                toex[i] = state.value[i]!!.value
            }
            UtilityClass.descriptionStates["scheduleMap"] = toex

        }, descriptionField = UtilityClass.descriptionMap["scheduleMap"]!!)
    }
}
