package com.example.firstprojecttry

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.firstprojecttry.Logic.Executor
import androidx.compose.ui.res.colorResource
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField.getField
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField.getFieldValue
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField.getObject
import com.example.firstprojecttry.Logic.descriptionMap
import com.example.firstprojecttry.Logic.descriptionStates
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.TimeZone


@Composable
fun LoadImageFromUrlExample(imageUrl: String, contentScale: ContentScale = ContentScale.None, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Translated description of what the image contains",
        modifier = modifier,
        contentScale = contentScale

    )
}

@Composable
fun ScheduleGrid(state: MutableState<HashMap<String, MutableState<Boolean>>>, changeable: Boolean = false) {
    val indexList = listOf(-1, 0, 1, 2, 3)
    val dayList = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")

    Column {
        for (index in indexList) {
            Row {
                val time = when (index) {
                    0 -> "Morning"
                    1 -> "Afternoon"
                    2 -> "Evening"
                    3 -> "Night"
                    else -> ""
                }
                Box(modifier = Modifier.width(130.dp)) {
                    Text(
                        text = time,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }
                for (day in dayList) {

                    Box(Modifier.padding(start = 5.dp, end = 5.dp, top = 7.dp)) {
                        if (index < 0) {
                            Surface(
                                shape = RectangleShape,
                                modifier = Modifier
                                    .animateContentSize()
                                    .padding(1.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(25.dp)
                                        .width(25.dp)
                                        .height(25.dp)
                                ) {
                                    Text(
                                        text = day,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 17.sp,
                                    )
                                }
                            }
                        } else {

                            // surfaceColor will be updated gradually from one color to the other
                            val surfaceColor by animateColorAsState(
                                if (state.value["$time + $day"]!!.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                            )
                            var borderColor by remember { mutableStateOf(Color.Black) } // track border color
                            Surface(
                                shape = RectangleShape,
                                // surfaceColor color will be changing gradually from primary to surface
                                color = surfaceColor,
                                // animateContentSize will change the Surface size gradually
                                modifier = Modifier
                                    .animateContentSize()
                                    .padding(1.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(25.dp)
                                        .border(width = 1.dp, color = borderColor)
                                        .clickable {
                                            if (changeable) {
                                                state.value["$time + $day"]!!.value =
                                                    !state.value["$time + $day"]!!.value
                                                borderColor =
                                                    if (borderColor == Color.Black) Color.White else Color.Black // toggle border color on click

                                            }

                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionItem(title: String, name: String, executor: MutableState<Executor>, last: Boolean = false, changeable: Boolean = true) {
    var textValue = "";
    println("Field " + name + " " + getField(name, executor.value))
    if (getFieldValue(name, executor.value) != null) {
        textValue = getFieldValue(name, executor.value).toString();
    }

    var text = rememberSaveable { mutableStateOf(textValue) }
    var selected = rememberSaveable{ mutableStateOf(false)}

    Surface(
        onClick = {
            if (descriptionMap[name]!!.changeable  && changeable) {
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
                        text = text.value,
                        color = colorResource(id = R.color.purple_500)
                    )
                },
                trailingContent = {

                    if (descriptionMap[name]!!.changeable && changeable) {
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
            descriptionField = Logic.descriptionMap[name]!!,
            fieldBody = text,
            selected = selected,
            onClickSave = {
                descriptionStates[name] = text.value
            })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacteristicChangeSheet(
    descriptionField : Logic.DescriptionCharacteristicField,
    fieldBody: MutableState<String>,
    selected: MutableState<Boolean>,
    singleLine: Boolean = true,
    onClickSave: () -> Unit,
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
            var text by rememberSaveable { mutableStateOf("") }
            var erorr by rememberSaveable { mutableStateOf(false) }
            var symbolsCount by rememberSaveable { mutableIntStateOf(fieldBody.value.length) }
            Column(
                modifier = Modifier.padding(bottom = 40.dp)
            ) {
                /*
                IconButton(
                    onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                selected.value = false
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "UI Icon Close Buttom Sheet"
                    )
                }

                 */
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
                            erorr = symbolsCount > restrictionSize
                        },
                        label = {
                            Text(descriptionField.textFieldTitle);
                        },
                        singleLine = singleLine,
                        isError = erorr,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 35.dp),
                        supportingText = {
                            Text(            modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End, text="$symbolsCount/$restrictionSize characters")
                        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridChangeSheet(state: MutableState<HashMap<String, MutableState<Boolean>>>, selected: MutableState<Boolean>, descriptionField: DescriptionCharacteristicField, onClickSave: () -> Unit) {
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
            var text by rememberSaveable { mutableStateOf("") }
            var erorr by rememberSaveable { mutableStateOf(false) }
            var symbolsCount by rememberSaveable { mutableIntStateOf(0) }
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
                    ScheduleGrid(state = state, true)
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
                    shape = RoundedCornerShape(4.dp)
                )
            }

        }
    }
}



@Composable
fun AvailabilityBlock(executor : MutableState<Executor>, changeable: Boolean = true) {
    var selectedGrid = rememberSaveable{ mutableStateOf(false)}

    var stateGrid = rememberSaveable{mutableStateOf(HashMap<String, MutableState<Boolean>>())}
    for (i in Logic.Schedule.keyNames) {
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
        var state = rememberSaveable{mutableStateOf(HashMap<String, MutableState<Boolean>>())}
        for (i in Logic.Schedule.keyNames) {
            state.value[i] = rememberSaveable{ mutableStateOf(stateGrid.value[i]!!.value) }
        }
        GridChangeSheet(state, selectedGrid, onClickSave = {
            var toex : MutableMap<String, Boolean> = HashMap();
            for (i in Logic.Schedule.keyNames) {
                stateGrid.value[i]!!.value = state.value[i]!!.value
                toex[i] = state.value[i]!!.value
            }
            descriptionStates["scheduleMap"] = toex

        }, descriptionField = descriptionMap["scheduleMap"]!!)
    }
}

@Composable
fun ProfilePage(executor: MutableState<Executor>, theme: MaterialTheme) {

    if (executor.value.photo.photoURL == null) {
        executor.value.photo.photoURL = ""
    }
    var photoURL = rememberSaveable {mutableStateOf(executor.value.photo.photoURL)}

    var imageUriState = remember{mutableStateOf<Uri?> (null)}
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUriState.value = uri
        ProfileViewModel.tryUploadImage(photoURL, imageUriState)
    }

    Scaffold(
        bottomBar = {
            BottomBar()
        }
    ) {
        paddingValues ->
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .size(45.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Surface(
                        onClick = {
                            descriptionStates["photoURL"] = photoURL.value;
                            ProfileViewModel.uploadExecutor(executor.value)
                        },

                        ) {
                        Text(
                            text = "Save",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(end = 20.dp)
                        )
                    }
                }
            }
            /*
            Text(
                text = "Edit Profile",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(start = 20.dp, top = 15.dp)
            )
             */
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.padding(bottom = 40.dp),
                ) {
                    Surface(
                        onClick = {
                            getContent.launch("image/*")
                        },
                        modifier = Modifier.graphicsLayer {
                            clip = true
                            shape = CircleShape
                            translationY = 30.dp.toPx()
                        }
                    ) {
                        LoadImageFromUrlExample(
                            imageUrl = photoURL.value,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(150.dp)
                                .align(Alignment.Start)

                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = colorResource(id = R.color.purple_200),
                        onClick = {
                            getContent.launch("image/*")

                        }

                    ) {
                        Row() {

                            Icon(painterResource(R.drawable.photo_icon_foreground), "Change photo UI", modifier = Modifier
                                .padding(start = 10.dp)
                                .width(20.dp))
                            Text(text = "Change", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(start = 5.dp, end = 10.dp))
                        }
                    }
                }
            }
            ListItem(
                headlineContent = {
                    Text(
                        "Personal information",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                supportingContent = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        DescriptionItem(title = "Legal name", name = "name", executor = executor)
                        DescriptionItem(title = "About you", name = "aboutYou", executor = executor)
                        DescriptionItem(title = "Number of children", name = "childNumber", executor = executor)
                        DescriptionItem(title = "Sex", name = "sex", executor = executor)

                    }
                }
            )
            AvailabilityBlock(executor = executor)


        }
    }

}


@Preview
@Composable
fun PreviewProfilePage() {
    var executor = remember{ mutableStateOf(Logic.Executor(
        4,
        "Elizabeth Mitchi",

        Logic.Description(),
        Logic.Schedule(),
        30,
        Logic.Photo(),
        Logic.Side.EXECUTORHOME,
        Logic.Location(50.065081, 19.923790)
    )) }
    ProfilePage(executor, MaterialTheme)
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewDate() {

    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            // Blocks Sunday and Saturday from being selected.
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val dayOfWeek = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                        .toLocalDate().dayOfWeek
                    dayOfWeek != DayOfWeek.SUNDAY && dayOfWeek != DayOfWeek.SATURDAY
                } else {
                    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    calendar.timeInMillis = utcTimeMillis
                    calendar[Calendar.DAY_OF_WEEK] != Calendar.SUNDAY &&
                            calendar[Calendar.DAY_OF_WEEK] != Calendar.SATURDAY
                }
            }

            // Allow selecting dates from year 2023 forward.
            override fun isSelectableYear(year: Int): Boolean {
                return year > 2022
            }
        }
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        DatePicker(state = datePickerState)
        Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
    }
}



@Composable
fun ExecutorCard(executor: MutableState<Executor>, theme: MaterialTheme = MaterialTheme, onGoBack : () -> Unit = {}, fromChat: Boolean = false ) {

    if (executor.value.photo.photoURL == null) {
        executor.value.photo.photoURL = ""
    }
    var photoURL = rememberSaveable {mutableStateOf(executor.value.photo.photoURL)}

    var imageUriState = remember{mutableStateOf<Uri?> (null)}
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUriState.value = uri
        ProfileViewModel.tryUploadImage(photoURL, imageUriState)
    }

    Scaffold(
    ) {
            paddingValues ->
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .size(45.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(
                    onClick = {
                        onGoBack()
                    }
                ) {
                    Icon( painter = painterResource(id = R.drawable.left_arrow),
                        contentDescription = "Go back from edit profile",
                        modifier = Modifier
                            .padding(start = 20.dp, top = 5.dp)
                            .height(30.dp)
                    )
                }
                Surface(
                    onClick = {
                        ProfileViewModel.showChatWith(executor.value)
                    }
                ) {
                    if (!fromChat) {
                        Text(
                            text = "Send message",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(end = 20.dp)
                        )
                    }
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.padding(bottom = 40.dp),
                ) {
                    Surface(
                        onClick = {
                        },
                        modifier = Modifier.graphicsLayer {
                            clip = true
                            shape = CircleShape
                            translationY = 30.dp.toPx()
                        }
                    ) {
                        LoadImageFromUrlExample(
                            imageUrl = photoURL.value,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(150.dp)
                                .align(Alignment.Start)

                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = colorResource(id = R.color.purple_200),
                    ) {
                    }
                }
            }
            ListItem(
                headlineContent = {
                    Text(
                        "Personal information",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                supportingContent = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        DescriptionItem(title = "Legal name", name = "name", executor = executor, changeable = false)
                        DescriptionItem(title = "About him", name = "aboutYou", executor = executor, changeable = false)
                        DescriptionItem(title = "Number of children", name = "childNumber", executor = executor, changeable = false)
                        DescriptionItem(title = "Sex", name = "sex", executor = executor, changeable = false)
                    }
                }
            )
            AvailabilityBlock(executor = executor, changeable = false)
        }
    }

}
