package com.example.firstprojecttry.Profile.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Logic.UtilityClass
import com.example.firstprojecttry.R
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun DescriptionItem(title: String, name: String, fieldValue: String?, last: Boolean = false, changeable: Boolean = true) {
    var textValue = "";
    var type = UtilityClass.descriptionMap[name]!!.type

    if (fieldValue != null) {
        textValue = fieldValue
    }

    var text = rememberSaveable { mutableStateOf(textValue) }
    var selected = rememberSaveable{ mutableStateOf(false) }

    Surface(
        onClick = {
            if (UtilityClass.descriptionMap[name]!!.changeable  && changeable) {
                selected.value = true;
            }

        }
    ) {
        Column() {
            ListItem(
                headlineContent = {
                    Text(
                        text = title
                    )
                },

                supportingContent = {
                    Text(
                        text = if (type.name == "DATE")  {
                            val date = Date(textValue.toLong())
                            val dateFormat = SimpleDateFormat("MM-dd-yyyy") // Define the desired date format
                            dateFormat.format(date)
                        }
                        else {
                            if(name == "price") text.value + "$"
                            else text.value},
                        color = colorResource(id = R.color.purple_500)
                    )
                },
                trailingContent = {

                    if (UtilityClass.descriptionMap[name]!!.changeable && changeable) {
                        Icon(
                            painter = painterResource(id = R.drawable.right_arrow),
                            contentDescription = "Name arrow",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }
            )
            if (!last) {
                Divider()
            }
        }
    }
    if (selected.value) {

        CharacteristicChangeSheet(
            descriptionField = UtilityClass.descriptionMap[name]!!,
            fieldBody = text,
            selected = selected,
            onClickSave = {
                UtilityClass.descriptionStates[name] = text.value
            },
            type = type
        )
    }
}