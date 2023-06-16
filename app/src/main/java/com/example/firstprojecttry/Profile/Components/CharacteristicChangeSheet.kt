package com.example.firstprojecttry.Profile.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField
import com.example.firstprojecttry.Logic.DescriptionType
import com.example.firstprojecttry.Profile.handleStringInput
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacteristicChangeSheet(
    descriptionField: DescriptionCharacteristicField,
    fieldBody: MutableState<String>,
    selected: MutableState<Boolean>,
    singleLine: Boolean = true,
    onClickSave: () -> Unit,
    type: DescriptionType,
) {
    var restrictionSize = descriptionField.restrictionValue
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    Box() {
        ModalBottomSheet(
            onDismissRequest = {
                selected.value = false
            },
            sheetState = bottomSheetState
        ) {
            var (erorr, changeError) = rememberSaveable{ mutableStateOf(false) }
            var symbolsCount by rememberSaveable { mutableIntStateOf(fieldBody.value.length) }
            Column(
                modifier = Modifier.padding(bottom = 40.dp)
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                ) {

                    Text(descriptionField.title,
                        fontSize = 30.sp,

                        );
                    Text(descriptionField.body, modifier = Modifier.padding(top = 10.dp),
                        fontSize = 20.sp,
                        color = Color.Gray
                    );

                    TextField(
                        value = fieldBody.value,
                        onValueChange = {
                            symbolsCount = it.length
                            fieldBody.value = it
                            erorr = false;

                            handleStringInput(fieldBody.value, changeError, type, restrictionSize, symbolsCount)
                        },
                        label = {
                            Text(descriptionField.textFieldTitle);
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
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 30.dp)
                )
                Button(
                    onClick = {
                        onClickSave()
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                selected.value = false
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    content = {
                        Text("Save")
                    },
                    shape = RoundedCornerShape(4.dp),
                    enabled = !erorr
                )
            }

        }
    }
}