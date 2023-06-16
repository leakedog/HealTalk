package com.example.firstprojecttry.GoogleMap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.firstprojecttry.ui.theme.Purple40
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}

//Maybe for executor show client? But we don't maintain client location :(
@Composable
fun getBitMapFromImage(
    context: Context,
    @DrawableRes iconResourceId: Int
): BitmapDescriptor? {
    return bitmapDescriptorFromVector(context, iconResourceId)
}


fun getBitMapFromText(message: String): BitmapDescriptor {
    val paintPurpleFill = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
        color = Purple40.toArgb()
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 30.dp.value
    }

    val paintTextWhite = Paint().apply {
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
        color = Color.White.toArgb()
        textAlign = Paint.Align.CENTER
        strokeWidth = 6.dp.value
        textSize = 48.dp.value
    }

    val paintWhite = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
        color = Color.White.toArgb()
        strokeWidth = 6.dp.value
    }

    val height = 150f
    val widthPadding = 80.dp.value
    val width = paintTextWhite.measureText(message, 0, message.length) + widthPadding
    val roundStart = height/3
    val path = Path().apply {
        arcTo(0f, 0f,
            roundStart * 2, roundStart * 2,
            -90f, -180f, true)
        lineTo(width/2 - roundStart / 2, height * 2/3)
        lineTo(width/2, height)
        lineTo(width/2 + roundStart / 2, height * 2/3)
        lineTo(width - roundStart, height * 2/3)
        arcTo(width - roundStart * 2, 0f,
            width, height * 2/3,
            90f, -180f, true)
        lineTo(roundStart, 0f)
    }

    val bm = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bm)
    canvas.drawPath(path, paintPurpleFill)
    canvas.drawPath(path, paintWhite)
    canvas.drawText(message, width/2, height * 2/3 * 2/3, paintTextWhite)
    return BitmapDescriptorFactory.fromBitmap(bm)
}

