package com.example.firstprojecttry.Login.Questions;

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Logic.DescriptionType
import com.example.firstprojecttry.Logic.UtilityClass.descriptionMap
import com.example.firstprojecttry.Logic.UtilityClass.descriptionStates
import com.example.firstprojecttry.handleStringInput


@Composable
fun StringQuestion(
    name: String,
    onError: (Boolean) -> Unit = {}
) {
    val field = descriptionMap[name]!!
    var type = field.type
    val restrictionSize = field.restrictionValue
    var his : String? =
        if (type != DescriptionType.NUMBER) {
            descriptionStates[name] as String?
        } else {
            if (descriptionStates[name] != null) {
                descriptionStates[name].toString()
            } else {
                null
            }
        }
    var idt by remember { mutableStateOf(name) }
    if (his == null) {
        his = "";
    }
    var text by remember { mutableStateOf(his) }
    var (erorr, changeError) = remember { mutableStateOf(false) }
    var symbolsCount by remember { mutableIntStateOf(text.length) }
    if (idt != name) {
        text = his;
        idt = name;
        symbolsCount = 0;
    }
    OutlinedTextField(
        value = text,
        onValueChange = {
            symbolsCount = it.length
            text = it
            handleStringInput(text, changeError, type, restrictionSize, symbolsCount, onError)

            if (!erorr) {
                descriptionStates[name] = text
            }
        },
        label = {
            Text(field.textFieldTitle)
        },
        singleLine = true,
        isError = erorr,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp),
        supportingText = {
            if (type == DescriptionType.STRING) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End, text = "$symbolsCount/$restrictionSize characters"
                )
            } else {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = if (erorr){
                        "Incorrect input"
                    } else {
                        ""
                    }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType =
            if (type == DescriptionType.STRING){
                KeyboardType.Text
            } else {
                KeyboardType.Number
            }

        )
    )
}
