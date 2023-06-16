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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField
import com.example.firstprojecttry.Logic.DescriptionType
import com.example.firstprojecttry.Logic.Executor
import com.example.firstprojecttry.Logic.UtilityClass
import com.example.firstprojecttry.Profile.Components.AvailabilityBlock
import com.example.firstprojecttry.Profile.Components.DescriptionItem
import com.example.firstprojecttry.Profile.Components.LoadImageFromUrlExample

import com.example.firstprojecttry.Profile.ProfileViewModel
import com.example.firstprojecttry.R

@Composable
fun ExecutorCard(executor: MutableState<Executor>, theme: MaterialTheme = MaterialTheme, onGoBack : () -> Unit = {}, fromChat: Boolean = false) {

    var listNames = UtilityClass.executorDescriptionNames;

    if (executor.value.photo.photoURL == null) {
        executor.value.photo.photoURL = ""
    }
    var photoURL = rememberSaveable { mutableStateOf(executor.value.photo.photoURL) }

    var imageUriState = remember{ mutableStateOf<Uri?> (null) }
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
                                .background(Color.LightGray)


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
                        for (name in listNames) {
                            val type = UtilityClass.descriptionMap[name]!!.type
                            if (name == "price"
                            ) {    DescriptionItem(title = UtilityClass.descriptionMap[name]!!.descriptionTitle, name = name, fieldValue = DescriptionCharacteristicField.getFieldValue(name, executor.value)
                                ?.toString(), changeable = false)
                            }
                        }
                        for (name in listNames) {
                            val type = UtilityClass.descriptionMap[name]!!.type
                            if (type in arrayListOf(
                                    DescriptionType.NUMBER, DescriptionType.STRING,
                                    DescriptionType.DATE, DescriptionType.CHECKBOX) &&
                                        name != "price"

                            ) {    DescriptionItem(title = UtilityClass.descriptionMap[name]!!.descriptionTitle, name = name, fieldValue = DescriptionCharacteristicField.getFieldValue(name, executor.value)
                                ?.toString(), changeable = false)
                            }
                        }

                    }
                }
            )
            AvailabilityBlock(executor = executor, changeable = false)
        }
    }

}

