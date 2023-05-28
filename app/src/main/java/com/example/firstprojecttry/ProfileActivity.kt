package com.example.firstprojecttry

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Paint.Align
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import coil.compose.AsyncImage
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.firstprojecttry.Logic.Description
import com.example.firstprojecttry.Logic.Executor
import com.google.firebase.concurrent.UiExecutor
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.TimeZone


@OptIn(ExperimentalGlideComposeApi::class)
@Preview
@Composable
fun ScreenProfile() {
    var imageUriState = remember{mutableStateOf<Uri?> (null)}
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUriState.value = uri
        ServerLogic.Login()
        ServerLogic.uploadImage(imageUriState)
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (imageUriState.value != null) {
                println(imageUriState.value);
                GlideImage(
                    model = imageUriState.value!!,
                    contentDescription = "Profile photo"
                );
            }
            Button(
                onClick = {
                    println("Debug");
                    getContent.launch("image/*")

                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                androidx.compose.material3.Text("Open Gallery");
            }
        }
    }


}



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
fun ScheduleGrid(executor: Executor) {
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
                            var isChanged by remember { mutableStateOf(executor.schedule.mp["$time + $day"]!!)}
                            // surfaceColor will be updated gradually from one color to the other
                            val surfaceColor by animateColorAsState(
                                if (isChanged) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
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
                                            isChanged = !isChanged
                                            borderColor =
                                                if (borderColor == Color.Black) Color.White else Color.Black // toggle border color on click

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
fun DescriptionItem(title: String, body: String?, executor: Executor, last: Boolean = false) {
    if (body != null) {
        var text = rememberSaveable { mutableStateOf("") }
        var selected = rememberSaveable{ mutableStateOf(false)}
        Surface(
            onClick = {
                selected.value = true;
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
                            text = body
                        )
                    },
                    trailingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.right_arrow),
                            contentDescription = "Name arrow",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                    )
                if (!last) {
                    Divider()
                }
            }
        }
        if (selected.value) {
            CharacteristicChangeSheet(title = "Title", description = "Description", fieldTitle = "Question" , fieldBody = text, selected = selected, onClickSave = {})
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacteristicChangeSheet(
    title: String,
    description: String,
    fieldTitle: String,
    fieldBody: MutableState<String>,
    selected: MutableState<Boolean>,
    singleLine: Boolean = true,
    onClickSave: () -> Unit,
    restrictionSize: Int = 20
) {
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
            var symbolsCount by rememberSaveable { mutableStateOf(0) }
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

                    Text(title,
                        fontSize = 30.sp,

                    );
                    Text(description, modifier = Modifier.padding(top = 10.dp),
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
                            Text(fieldTitle);
                        },
                        singleLine = singleLine,
                        isError = erorr,
                        modifier = Modifier.fillMaxWidth().padding(top = 35.dp),
                        supportingText = {
                            Text(            modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End, text="$symbolsCount/$restrictionSize characters")
                        }
                    )
                }
                Divider(
                    modifier = Modifier.fillMaxWidth().padding(top = 30.dp, bottom = 30.dp)
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
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth(),
                    content = {
                        Text("Save")
                    },
                    shape = RoundedCornerShape(4.dp)
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewCharacteristicChangeSheet() {
    var text = remember { mutableStateOf("") };
    CharacteristicChangeSheet(title = "Title", description = "Description", fieldTitle = "Question" , fieldBody = text, onClickSave = {}, selected = mutableStateOf(false))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(executor: Executor, theme: MaterialTheme) {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .size(45.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            ) {
            Icon(
                painter = painterResource(id = R.drawable.left_arrow),
                contentDescription = "Go back from edit profile",
                modifier = Modifier
                    .padding(start = 20.dp, top = 5.dp)
                    .height(30.dp)
            )
            Text(
                text = "Save",
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 20.dp)
            )
        }
        Text(
            text = "Edit Profile",
            fontSize = 35.sp,
            modifier = Modifier.padding(start = 20.dp, top = 15.dp)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .background(Color.LightGray),
            contentAlignment = Alignment.TopCenter
            ) {
            Icon(
                painter = painterResource(id = R.drawable.edit),
                contentDescription = "Profile Setting Photo",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 30.dp, end = 40.dp)
                    .size(22.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                LoadImageFromUrlExample(
                    imageUrl = "https://images.pexels.com/photos/774909/pexels-photo-774909.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.Start)
                )

                Text(
                    text = "PROFILE PHOTO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 20.dp, top = (15).dp)
                        .align(Alignment.Start)
                )
            }
        }
        ListItem(
            headlineContent = {
                Text(
                    "Personal information"
                )
            },
            supportingContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    DescriptionItem(title = "Legal name", body = executor.name, executor = executor)
                    DescriptionItem(title = "Email", body = executor.name, executor = executor)
                    DescriptionItem(title = "Address", body = "Krakow", executor = executor)
                    DescriptionItem(
                        title = "Government ID",
                        body = "Krakow",
                        executor = executor,
                        last = true
                    )
                }
            }
        )
        ListItem(
            headlineContent = {
                Text(
                    "Availability"
                )
            },
            supportingContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ScheduleGrid(executor)
                }
            }
        )


    }
}


@Preview
@Composable
fun PreviewProfilePage() {
    ProfilePage(Logic.Executor(
        4,
        "Elizabeth Mitchi",

        Logic.Description("I am a college graduate from SUNY Cortland. I have a degree in Sociology and Spanish, with a minor in Latin American Studies. I am in the process of pursuing a Masters in Social Work. I am also a Spanish tutor."
        ),
        Logic.Schedule(),
        30,
        Logic.Photo(),
        Logic.Side.EXECUTORHOME,
        Logic.Location(50.065081, 19.923790)
    ), MaterialTheme)
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