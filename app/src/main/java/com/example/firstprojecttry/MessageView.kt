package com.example.firstprojecttry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstprojecttry.ui.theme.FirstProjectTryTheme
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import coil.compose.AsyncImage
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.firstprojecttry.Logic.Description
import com.example.firstprojecttry.Logic.Executor
import com.example.firstprojecttry.Logic.Photo
import com.google.firebase.concurrent.UiExecutor
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.TimeZone

import com.example.firstprojecttry.Messenger
import com.google.firebase.firestore.auth.User
import java.lang.Math.floor

/*
@Composable
fun LoadImageFromUrlExample(imageUrl: String, contentScale: ContentScale = ContentScale.None, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Translated description of what the image contains",
        modifier = modifier,
        contentScale = contentScale

    )
}
*/
@Composable
fun messageLength(text : String, lineHeight: TextUnit) : Dp{
    var lineHeightPx : Int = with(LocalDensity.current){lineHeight.roundToPx()}
    val ourLimit : Int = with(LocalDensity.current){(getHeight() - 100).dp.roundToPx()}
    lineHeightPx = floor((lineHeightPx) * 1.0).toInt()
    if(text.length * lineHeightPx >= ourLimit){
        return (getHeight()-100).dp
    }else{
        val transform : Dp = with(LocalDensity.current){(text.length * lineHeightPx).toDp()}
        return transform
    }
}
public val messageBackOpp = Color(0xFFEBE4ED)
public val messageBackView = Color(0XFFA731CA)
public val messageFont = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

@Composable
fun DisplayMessage(text : String, side : UserMessageSide, photo : Photo){


    val messageLen : Dp = messageLength(text, messageFont.fontSize)

    val photoMod = Modifier
        .size(40.dp)
        .clip(CircleShape)
    if(side == UserMessageSide.RECEIVER){
        Row(
            modifier = Modifier
                .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
             Spacer(modifier = Modifier.width(8.dp))
             LoadImageFromUrlExample(
                imageUrl = photo.getPhotoURL(),
                modifier = photoMod
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.width((getWidth() - 100).dp), horizontalAlignment = Alignment.Start) {
                Spacer(Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                        .background(color = messageBackOpp)
                ) {
                    Text(
                        text = text,
                        style = messageFont,
                        modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp),
                    );
                }
            }


        }
    }else{

            Row(
                modifier = Modifier
                    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(modifier = Modifier.width((getWidth() - 100).dp), horizontalAlignment = Alignment.End) {
                    Spacer(Modifier.height(5.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                            .background(color = messageBackView)
                    ) {
                        Text(
                            text = text,
                            style = messageFont,
                            modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp),
                        );
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                LoadImageFromUrlExample(
                    imageUrl = photo.getPhotoURL(),
                    modifier = photoMod
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

    }

}
@Preview
@Composable
fun showHowLook() {
    Column {
        DisplayMessage(
            "Hey",
            UserMessageSide.RECEIVER,
           Photo("https://stats.ioinformatics.org/img/photos/2022/7678.jpg")
        )
        DisplayMessage(
            "Wassup",
            UserMessageSide.SENDER,
            Photo("https://media.licdn.com/dms/image/D4D03AQHu2ES2QbvZgg/profile-displayphoto-shrink_800_800/0/1666369413666?e=2147483647&v=beta&t=l6mqyt4ks3YTB4Uqrx_fFRzHtlpiSqc35TROjomRAI4")
        )
    }
}