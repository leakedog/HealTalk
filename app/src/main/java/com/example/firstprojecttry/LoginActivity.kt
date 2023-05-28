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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
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
import com.google.firebase.concurrent.UiExecutor
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.TimeZone



@Composable
fun GreetingScreen(navController: NavController) {
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
                    navController.navigate("login")
                },
            ) {
                Text("Login", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold);
            }
            Button(
                onClick = {
                    navController.navigate("registration")
                },
            ) {
                Text("Sign Up", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold);
            }
        }
        Text("Or via social media", style = MaterialTheme.typography.labelLarge, color = Color.DarkGray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(

                modifier = Modifier.size(50.dp),
                onClick = {

                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    modifier = Modifier.size(50.dp),
                    contentDescription = "Go back from edit profile",
                )
            }

        }
    }
}


@Composable
fun LoginScreen(navController: NavController) {
    var erorr = remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()
        .background(Color.White)) {
        IconButton(

            modifier = Modifier.padding(top = 20.dp, start = 15.dp).size(50.dp),
            onClick = {
                navController.navigate("greeting")
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
                modifier = Modifier.padding(top = 40.dp).fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                "Sign in to continue",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth(),
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
                isError = erorr.value
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
                modifier = Modifier.padding(top = 60.dp).width(220.dp)
            ) {
                Text("Login Now")
            }
            Text(
                text = "Forgot Password?",
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium, color = Color.Gray

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(

                    modifier = Modifier.size(50.dp),
                    onClick = {

                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        modifier = Modifier.size(50.dp),
                        contentDescription = "Go back from edit profile",
                    )
                }

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
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate("registration")

                    }).padding(start = 5.dp)
                )
            }

        }
    }
}
@Composable
fun SignUpScreen(navController: NavController) {
    var erorr = remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        IconButton(

            modifier = Modifier.padding(top = 20.dp, start = 15.dp).size(50.dp),
            onClick = {
                navController.navigate("greeting")
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
                "Hi!",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 40.dp).fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                "Create a new account",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth(),
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
                isError = erorr.value,
                supportingText = {
                    if (erorr.value) {
                        Text("Incorrect email")
                    }
                }
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("Password", color = Color.DarkGray) },
                isError = erorr.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Button(
                onClick = {
                    AuthViewModel.tryRegister(email, password, erorr)
                },
                modifier = Modifier.padding(top = 60.dp).width(220.dp)
            ) {
                Text("Sign Up")
            }
            Text(
                text = "Forgot Password?",
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium, color = Color.Gray

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(

                    modifier = Modifier.size(50.dp),
                    onClick = {

                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        modifier = Modifier.size(50.dp),
                        contentDescription = "Go back from edit profile",
                    )
                }

            }
            Row(
                Modifier.padding(top = 30.dp)
            ) {
                Text(
                    "Already have an account?",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.LightGray
                )
                Text(
                    "Sign in",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate("login")
                    }).padding(start = 5.dp)
                )
            }

        }
    }
}