package com.example.firstprojecttry.Profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstprojecttry.helperActivities.BottomBar
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField
import com.example.firstprojecttry.Logic.DescriptionType
import com.example.firstprojecttry.Logic.Executor
import com.example.firstprojecttry.Logic.UtilityClass
import com.example.firstprojecttry.Profile.Components.AvailabilityBlock
import com.example.firstprojecttry.Profile.Components.LogOutButton
import com.example.firstprojecttry.R


@Composable
fun ProfileExecutorPage(executor: MutableState<Executor>, theme: MaterialTheme = MaterialTheme) {
    UtilityClass.descriptionStates.clear();
    val listNames = UtilityClass.executorDescriptionNames
    if (executor.value.photo.photoURL == null) {
        executor.value.photo.photoURL = ""
    }

    val photoURL = rememberSaveable { mutableStateOf(executor.value.photo.photoURL) }

    val imageUriState = remember{ mutableStateOf<Uri?> (null) }
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
                            UtilityClass.descriptionStates["photoURL"] = photoURL.value
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

                            Icon(
                                painterResource(R.drawable.photo_icon_foreground), "Change photo UI", modifier = Modifier
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
                            val type = UtilityClass.descriptionMap[name]!!.type
                            if (type in arrayListOf<DescriptionType>(
                                    DescriptionType.NUMBER, DescriptionType.STRING,
                                    DescriptionType.DATE, DescriptionType.CHECKBOX)
                            ) {
                                DescriptionItem(title = UtilityClass.descriptionMap[name]!!.descriptionTitle, name = name, fieldValue = DescriptionCharacteristicField.getFieldValue(name, executor.value)
                                    ?.toString())
                            }
                        }
                    }
                }
            )
            AvailabilityBlock(executor = executor)
            LogOutButton()
        }
    }

}
