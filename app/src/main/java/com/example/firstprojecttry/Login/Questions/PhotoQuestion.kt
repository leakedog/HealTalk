package com.example.firstprojecttry.Login.Questions

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Logic.UtilityClass.descriptionMap
import com.example.firstprojecttry.Logic.UtilityClass.descriptionStates
import com.example.firstprojecttry.Profile.Components.LoadImageFromUrlExample
import com.example.firstprojecttry.Profile.ProfileViewModel
import com.example.firstprojecttry.R


@Preview
@Composable
fun PhotoQuestion(
    name: String = "photoURL"
) {
    val field = descriptionMap[name]!!
    var photoURL = rememberSaveable { mutableStateOf("") }
    descriptionStates[name] = photoURL.value
    var imageUriState = remember{ mutableStateOf<Uri?> (null) }
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUriState.value = uri
        ProfileViewModel.tryUploadImage(photoURL, imageUriState)
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
                    Text(text = "Upload", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(start = 5.dp, end = 10.dp))
                }
            }
        }
    }
}

