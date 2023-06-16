package com.example.firstprojecttry.Profile

import android.net.Uri
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.firstprojecttry.helperActivities.BottomBar
import com.example.firstprojecttry.Logic.Client
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField.getFieldValue
import com.example.firstprojecttry.Logic.DescriptionType
import com.example.firstprojecttry.Logic.Executor
import com.example.firstprojecttry.Logic.UserType
import com.example.firstprojecttry.Logic.UtilityClass.clientDescriptionNames
import com.example.firstprojecttry.Logic.UtilityClass.descriptionMap
import com.example.firstprojecttry.Logic.UtilityClass.descriptionStates
import com.example.firstprojecttry.Login.AuthViewModel
import com.example.firstprojecttry.Profile.Components.CharacteristicChangeSheet
import com.example.firstprojecttry.Profile.Components.LogOutButton
import com.example.firstprojecttry.R
import java.text.SimpleDateFormat
import java.util.Date


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


@Composable
fun DescriptionItem(title: String, name: String, fieldValue: String?, last: Boolean = false, changeable: Boolean = true) {
    var textValue = "";
    var type = descriptionMap[name]!!.type

    if (fieldValue != null) {
        textValue = fieldValue
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
            descriptionField = descriptionMap[name]!!,
            fieldBody = text,
            selected = selected,
            onClickSave = {
                descriptionStates[name] = text.value
            },
            type = type
        )
    }
}


fun handleStringInput(
    fieldBody: String,
    changeError: (Boolean) -> (Unit) = {},
    type: DescriptionType,
    restrictionSize: Int,
    symbolsCount: Int,
    onError : (Boolean) -> (Unit) = {}
) {
    changeError(false);
    onError(true);
    if (type == DescriptionType.STRING) {
        changeError(symbolsCount > restrictionSize)
        onError(symbolsCount <= restrictionSize);
    } else {
        if ( fieldBody  == "" || (fieldBody.length > 1 && fieldBody[0] == '0')) {
            changeError(true);
            onError(false);

        }
        try{
            val x =  fieldBody.toInt()
            if (x > restrictionSize) {
                changeError(true);
                onError(false);

            }
        } catch (e : Exception) {
            changeError(true);
            onError(false);
        }
    }
}




@Preview
@Composable
fun PreviewProfilePage() {


    if (AuthViewModel.getCurrentUserType() == UserType.CLIENT) {
        val user = remember {
            mutableStateOf(AuthViewModel.getCurrentUser() as Client)
        }
        ProfileClientPage(client = user)
    } else {
        val value = AuthViewModel.getCurrentUser() as Executor
        val user = remember {
            mutableStateOf(value)
        }
        ProfileExecutorPage(executor = user)
    }
}







@Composable
fun ProfileClientPage(client: MutableState<Client>, theme: MaterialTheme = MaterialTheme) {
    descriptionStates.clear();
    var listNames = clientDescriptionNames;
    if (client.value.photo.photoURL == null) {
        client.value.photo.photoURL = ""
    }
    var photoURL = rememberSaveable {mutableStateOf(client.value.photo.photoURL)}

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
                    .padding(top = 30.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Edit Profile",
                    style = MaterialTheme.typography.displayMedium,
                )
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Surface(
                        onClick = {
                            descriptionStates["photoURL"] = photoURL.value;
                            ProfileViewModel.uploadUser()
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
                                .background(Color.LightGray)

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
                        for (name in listNames) {
                            val type = descriptionMap[name]!!.type
                            if (type in arrayListOf<DescriptionType>(DescriptionType.NUMBER, DescriptionType.STRING,
                                    DescriptionType.DATE, DescriptionType.CHECKBOX)
                            ) {
                                DescriptionItem(title = descriptionMap[name]!!.descriptionTitle, name = name, fieldValue = getFieldValue(name, client.value)?.toString())
                            }
                        }
                    }
                }
            )
            LogOutButton()
        }
    }

}



