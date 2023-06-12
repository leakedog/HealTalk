package com.example.firstprojecttry.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.R
import com.example.firstprojecttry.ui.theme.Pink40
import com.example.firstprojecttry.ui.theme.Pink80
import com.example.firstprojecttry.ui.theme.Purple40
import com.example.firstprojecttry.ui.theme.Purple80


@Preview
@Composable
fun ChooseTypePage() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val startColor = Pink40// Replace with your desired start color
        val endColor = Purple40 // Replace with your desired end color

        val gradientBrush = Brush.horizontalGradient(
            colors = listOf(startColor, endColor)
        )

        val text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    brush  = gradientBrush
                )
            ) {
                append("Welcome")
            }
        }
        Text(text,
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.ExtraBold)
        Row(modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxWidth()) {

            Surface(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.5f),
                onClick = {
                    AuthViewModel.goAsClient();
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.client_choosing),
                        contentDescription = "executor UI choose start",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(Pink40, blendMode = BlendMode.Color)
                    )
                    Text("I'm a parent", style = MaterialTheme.typography.headlineMedium, color = Pink80, fontWeight = FontWeight.Bold)
                }

            }

            Surface(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(1f),
                onClick = {
                    AuthViewModel.goAsExecutor();
                }

            ) {
                Column(
                    Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.executor_playing),
                        contentDescription = "client UI choose start",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(Purple40, blendMode = BlendMode.Color)
                    )
                    Text("I'm a sitter", style = MaterialTheme.typography.headlineMedium, color = Purple80, fontWeight = FontWeight.Bold)

                }

            }

        }
    }
}