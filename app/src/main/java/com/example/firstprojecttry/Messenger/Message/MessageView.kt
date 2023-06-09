package com.example.firstprojecttry.Messenger.Message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstprojecttry.Logic.Photo
import com.example.firstprojecttry.Profile.Components.LoadImageFromUrlExample
import com.example.firstprojecttry.UserMessageSide
import com.example.firstprojecttry.getHeight
import com.example.firstprojecttry.getWidth
import java.lang.Math.floor


@Composable
fun messageLength(text : String, lineHeight: TextUnit) : Dp{
    var lineHeightPx : Int = with(LocalDensity.current){lineHeight.roundToPx()}
    val ourLimit : Int = with(LocalDensity.current){(getHeight() - 100).dp.roundToPx()}
    lineHeightPx = floor((lineHeightPx) * 1.0).toInt()
    if(text.length * lineHeightPx >= ourLimit){
        return (getHeight() -100).dp
    }else{
        val transform : Dp = with(LocalDensity.current){(text.length * lineHeightPx).toDp()}
        return transform
    }
}
 val messageBackOpp = Color(0xFFEBE4ED)
 val messageBackView = Color(0XFFA731CA)
 val messageFont = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

@Composable
fun DisplayMessage(text : String, side : UserMessageSide, photo : Photo){

    val bottomHeight = 100.dp
    val textPadding = 5.dp
    val messageCircleBorder = 10.dp
    val marginAboveMessage = 5.dp
    val widthBetweenImage = 8.dp
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
             Spacer(modifier = Modifier.width(widthBetweenImage))
             LoadImageFromUrlExample(
                imageUrl = photo.getPhotoURL(),
                modifier = photoMod
            )
            Spacer(modifier = Modifier.width(widthBetweenImage))
            Column(modifier = Modifier.width(getWidth().dp - bottomHeight), horizontalAlignment = Alignment.Start) {
                Spacer(Modifier.height(marginAboveMessage))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(messageCircleBorder, messageCircleBorder, messageCircleBorder, messageCircleBorder))
                        .background(color = messageBackOpp)
                ) {
                    Text(
                        text = text,
                        style = messageFont,
                        modifier = Modifier.padding(textPadding, textPadding, textPadding, textPadding),
                    );
                }
            }
        }
    }else{

            Row(
                modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(modifier = Modifier.width(getWidth().dp - bottomHeight), horizontalAlignment = Alignment.End) {
                    Spacer(Modifier.height(marginAboveMessage))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(messageCircleBorder, messageCircleBorder, messageCircleBorder, messageCircleBorder))
                            .background(color = messageBackView)
                    ) {
                        Text(
                            text = text,
                            style = messageFont,
                            modifier = Modifier.padding(textPadding, textPadding, textPadding, textPadding),
                        );
                    }
                }
                Spacer(modifier = Modifier.width(widthBetweenImage))
                LoadImageFromUrlExample(
                    imageUrl = photo.getPhotoURL(),
                    modifier = photoMod
                )
                Spacer(modifier = Modifier.width(widthBetweenImage))
            }
    }
}