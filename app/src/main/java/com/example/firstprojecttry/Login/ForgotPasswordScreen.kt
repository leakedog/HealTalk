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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Preview
@Composable
fun SnackTest() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            var clickCount by remember { mutableStateOf(0) }
            ExtendedFloatingActionButton(
                onClick = {
                    // show snackbar as a suspend function
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Snackbar # ${++clickCount}",
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                }
            ) { Text("Show snackbar") }
        },
        content = { innerPadding ->
            Text(
                text = "Body content",
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }
    )
}


@Preview
@Composable
fun ForgotPasswordScreen() {
    var erorr = remember { mutableStateOf(false) }
    var success =  remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },

        content = {
            paddingValues ->
            Column (
                Modifier.padding(paddingValues).fillMaxSize()
                    .background(Color.White)
            ) {
                IconButton(

                    modifier = Modifier
                        .padding(top = 20.dp, start = 15.dp)
                        .size(50.dp),
                    onClick = {
                        AuthViewModel.goToLogin()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        modifier = Modifier.size(50.dp),
                        contentDescription = "Go back to Login Button UI",
                    )
                }
                Column(
                    Modifier
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "Forgot password?",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        "Change it using your email",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    var email by rememberSaveable { mutableStateOf("") }
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            erorr.value = false;
                        },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp),
                        isError = erorr.value,
                        supportingText = {
                            if (erorr.value) {
                                Text("Incorrect email")
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )
                    Button(
                        onClick = {
                            AuthViewModel.tryChangePassword(email, erorr, success)
                            if (success.value) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Successfully changed password! Check your email for the link.",
                                        duration = SnackbarDuration.Long,
                                        withDismissAction = true,

                                        )
                                }
                            }
                        },
                        modifier = Modifier
                            .padding(top = 60.dp)
                            .width(220.dp)
                    ) {
                        Text("Change password")
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
    )

}