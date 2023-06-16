package com.example.firstprojecttry.Login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Navigator.NavigatorModel
import com.example.firstprojecttry.R

@Preview
@Composable
fun GreetingScreen() {

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        AuthViewModel.handleActivityCode(result.resultCode)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(id = R.drawable.login_firt),
            contentDescription = "Greetings Login Image UI",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text("Hello!", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Text("Welcome to Agu The Best Babysitting Platform", style = MaterialTheme.typography.titleMedium, color = Color.Gray, modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(), textAlign = TextAlign.Center)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    NavigatorModel.showLogin()
                },
            ) {
                Text("Login", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            }
            Button(
                onClick = {
                    NavigatorModel.showRegistration()
                },
            ) {
                Text("Sign Up", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            }
        }
        Text("Or via social media", style = MaterialTheme.typography.labelLarge, color = Color.DarkGray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
        ) {
            SocialMediaIcons(size = 50.dp)

        }
    }
}

