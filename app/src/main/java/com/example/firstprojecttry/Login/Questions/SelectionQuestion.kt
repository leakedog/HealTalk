package com.example.firstprojecttry.Login.Questions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Logic.SexType
import com.example.firstprojecttry.Logic.UtilityClass.descriptionMap
import com.example.firstprojecttry.Logic.UtilityClass.descriptionStates

@Composable
fun SelectionQuestion(
    name: String
) {
    val field = descriptionMap[name]!!
    var state by remember { mutableStateOf(
        (descriptionStates[name] == null) ||
                (descriptionStates[name] == SexType.MALE)
    ) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.selectableGroup(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = state,
                onCheckedChange = {
                    state = !state
                    if (state) {
                        descriptionStates[name] = SexType.MALE
                    } else {
                        descriptionStates[name] = SexType.FEMALE
                    }
                },

                )
            var text = ""
            text = if (state) {
                "Male"
            } else {
                "Female"
            }
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}
