package com.example.firstprojecttry.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreen() {
    var erorr = remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        IconButton(

            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp)
                .size(50.dp),
            onClick = {
                AuthViewModel.goBackToStart()
            },
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier.size(50.dp),
                contentDescription = "Go back to Greetings Button UI",
            )
        }
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome Back!",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                "Sign in to continue",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    erorr.value = false
                },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                supportingText = {
                    if (erorr.value) {
                        Text("Wrong email")
                    }
                },
                isError = erorr.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    erorr.value = false
                },
                label = { Text("Password", color = Color.DarkGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                isError = erorr.value,
                supportingText = {
                    if (erorr.value) {
                        Text("Or wrong password")
                    }
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Button(
                onClick = {
                    AuthViewModel.tryLogin(email, password, erorr)
                },
                modifier = Modifier
                    .padding(top = 60.dp)
                    .width(220.dp)
            ) {
                Text("Login Now")
            }
            Surface(onClick = {
                AuthViewModel.goToForgotPassword();
            }
            ) {
                Text(
                    text = "Forgot Password?",
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium, color = Color.Gray

                )
            }

            Row(
                Modifier.padding(top = 30.dp)
            ) {
                Text(
                    "Don't have an account?",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.LightGray
                )
                Text(
                    "Sign up",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    modifier = Modifier
                        .clickable(onClick = {
                            AuthViewModel.goToSignUp();
                        })
                        .padding(start = 5.dp)
                )
            }

        }
    }
}
