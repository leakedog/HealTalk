package com.example.firstprojecttry.helperActivities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstprojecttry.R
import com.example.firstprojecttry.ui.theme.FirstProjectTryTheme
import kotlinx.coroutines.delay

class LoadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstProjectTryTheme {
                // A surface container using the 'background' color from the theme
               LoadingBlock()
            }
        }
    }
}

@Composable
fun LoadingBlock() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo1), modifier =
            Modifier
                .padding(paddingValues = PaddingValues(top = 150.dp))
                .size(170.dp)
            ,
            contentDescription = "Logo loading",
            contentScale = ContentScale.Fit
        )

        LoadingButt()
    }

}

@Composable
fun LoadingButt() {
    val numberOfDots = 3
    val dotAnimationState = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            repeat(numberOfDots) { index ->
                dotAnimationState.value = index
                delay(400)
            }
        }
    }

    val animatedAlpha = animateFloatAsState(
        targetValue = if (dotAnimationState.value == numberOfDots - 1) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    ).value

    val loadingText = buildString {
        append("Loading")
        repeat(dotAnimationState.value + 1) {
            append(".")
        }
    }

    LoadingText(text = loadingText, alpha = animatedAlpha)
}

@Composable
fun LoadingText(text: String, alpha: Float) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = text,
            style = TextStyle(fontSize = 40.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstProjectTryTheme {
        LoadingBlock()
    }
}