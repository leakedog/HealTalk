package com.example.firstprojecttry.helperActivities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Login.AuthViewModel
import com.example.firstprojecttry.Profile.ProfileViewModel
import com.example.firstprojecttry.R

@Preview
@Composable
fun ErrorScreen() {
    Column(Modifier.fillMaxSize().background(Color.White)) {
        Column(Modifier.padding(start = 20.dp, end = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.error),
                contentDescription = "Error Image UI",
                modifier = Modifier.fillMaxWidth().padding(top = 100.dp),
                contentScale = ContentScale.FillWidth,

            )
            Text("We're sorry", modifier = Modifier.padding(start = 20.dp).fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineLarge, color = Color.Black)
            Column(Modifier.padding(start = 40.dp, end = 40.dp, top = 20.dp).fillMaxWidth()) {
                Text("We've been trying to process your request, but couldn't do it", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center, color = Color.LightGray)
            }
            Button(
                onClick = {
                    AuthViewModel.handleComeBackFromError()
                },
                modifier = Modifier.padding(top = 50.dp)
            ){
                Text("Try again", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview
@Composable
fun ErrorImageScreen() {
    Column(Modifier.fillMaxSize().background(Color.White)) {
        Column(Modifier.padding(start = 20.dp, end = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.error),
                contentDescription = "Error Image UI",
                modifier = Modifier.fillMaxWidth().padding(top = 100.dp),
                contentScale = ContentScale.FillWidth,

                )
            Text("We're sorry", modifier = Modifier.padding(start = 20.dp).fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineLarge, color = Color.Black)
            Column(Modifier.padding(start = 40.dp, end = 40.dp, top = 20.dp).fillMaxWidth()) {
                Text("We've been trying to process your request, but couldn't do it", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center, color = Color.LightGray)
            }
            Button(
                onClick = {
                    ProfileViewModel.handleComeBackFromError()
                },
                modifier = Modifier.padding(top = 50.dp)
            ){
                Text("Try again", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
        }
    }
}

