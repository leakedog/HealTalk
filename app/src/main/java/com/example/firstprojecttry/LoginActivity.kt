package com.example.firstprojecttry

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firstprojecttry.Logic.descriptionMap
import com.example.firstprojecttry.Logic.descriptionNames
import com.example.firstprojecttry.Logic.descriptionStates
import com.example.firstprojecttry.ui.theme.Pink40
import com.example.firstprojecttry.ui.theme.Pink80
import com.example.firstprojecttry.ui.theme.Purple40
import com.example.firstprojecttry.ui.theme.Purple80
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.NumberFormatException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


@Preview
@Composable
fun GreetingScreen() {
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
                    AuthViewModel.navController.navigate("login")
                },
            ) {
                Text("Login", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold);
            }
            Button(
                onClick = {
                    AuthViewModel.navController.navigate("registration")
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
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        IconButton(

            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp)
                .size(50.dp),
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
            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
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
                    modifier = Modifier
                        .clickable(onClick = {
                            navController.navigate("registration")

                        })
                        .padding(start = 5.dp)
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

            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp)
                .size(50.dp),
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
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                "Create a new account",
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
                modifier = Modifier
                    .padding(top = 60.dp)
                    .width(220.dp)
            ) {
                Text("Sign Up")
            }
            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
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
                    modifier = Modifier
                        .clickable(onClick = {
                            navController.navigate("login")
                        })
                        .padding(start = 5.dp)
                )
            }

        }
    }
}


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


@Composable
fun PageIndicatorView(
    id: Int,
    isSelected: Boolean,
    selectedColor: Color,
    defaultColor: Color,
    defaultRadius: Dp,
    selectedLength: Dp,
    animationDurationInMillis: Int,
    modifier: Modifier = Modifier,
    onClick : (Int) -> Unit = {},
) {

    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            selectedColor
        } else {
            defaultColor
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        )
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            selectedLength
        } else {
            defaultRadius
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        )
    )

    Surface(onClick = {
        onClick(id);
    }) {
        Canvas(
            modifier = modifier
                .size(
                    width = width,
                    height = defaultRadius,
                ),
        ) {
            drawRoundRect(
                color = color,
                topLeft = Offset.Zero,
                size = Size(
                    width = width.toPx(),
                    height = defaultRadius.toPx(),
                ),
                cornerRadius = CornerRadius(
                    x = defaultRadius.toPx(),
                    y = defaultRadius.toPx(),
                ),
            )
        }
    }

}



@Composable
fun StringQuestion(
    id: Int,
    onError: (Boolean) -> Unit = {}
) {
    val name = Logic.descriptionNames[id]!!
    val field = Logic.descriptionMap[name]!!
    var type = field.type
    val restrictionSize = field.restrictionValue
    println("EBLA")
    var his : String? =
        if (type != Logic.DescriptionType.NUMBER) {
            Logic.descriptionStates[name] as String?
        } else {
            if (Logic.descriptionStates[name] != null) {
                Logic.descriptionStates[name].toString()
            } else {
                null
            }
        }
    println(name)
    var idt by remember { mutableStateOf(id) }
    if (his == null) {
        his = "";
    }
    var text by remember { mutableStateOf(his) }
    var erorr by remember { mutableStateOf(false) }
    var symbolsCount by remember { mutableIntStateOf(text.length) }
    if (idt != id) {
        text = his;
        idt = id;
        symbolsCount = 0;
    }
    OutlinedTextField(
        value = text,
        onValueChange = {
            symbolsCount = it.length
            text = it
            if (type == Logic.DescriptionType.STRING) {
                Logic.descriptionStates[name] = text
                if (!field.changeable && symbolsCount == 0) {
                    erorr = true
                }
            } else {
                if (text == "") {
                    erorr = true;
                }
                try{
                    val x = text.toInt()
                } catch (e : Exception) {
                    erorr = true
                }
                if (!erorr) {
                    Logic.descriptionStates[name] = text.toInt()
                }
            }

            erorr = symbolsCount > restrictionSize
            onError(!erorr)
        },
        label = {
            Text(field.textFieldTitle)
        },
        singleLine = true,
        isError = erorr,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp),
        supportingText = {
            if (type == Logic.DescriptionType.STRING) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End, text = "$symbolsCount/$restrictionSize characters"
                )
            } else {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = if (erorr){
                        "Incorrect input"
                    } else {
                        ""
                    }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType =
                if (type == Logic.DescriptionType.STRING){
                    KeyboardType.Text
                } else {
                    KeyboardType.Number
                }

        )
    )
}

@Composable
fun PageIndicatorQuestions(
    modifier: Modifier = Modifier,
    selectedPage: Int = 0,
    selectedColor: Color = Color.Blue,
    defaultColor: Color = Color.LightGray,
    defaultRadius: Dp = 20.dp,
    selectedLength: Dp = 60.dp,
    space: Dp = 10.dp,
    animationDurationInMillis: Int = 300,
    onClick : (Int) -> Unit = {},
    onFinish : () -> Unit = {}
) {
    var (isEnabledValue, changeEnabled) = remember { mutableStateOf(false)}
    val numberOfPages = Logic.descriptionMap.size
    var stateGrid = rememberSaveable{mutableStateOf(HashMap<String, MutableState<Boolean>>())}
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(space),
                modifier = modifier,

                ) {
                for (i in 0 until numberOfPages) {
                    val isSelected = (i == selectedPage)
                    PageIndicatorView(
                        id = i,
                        isSelected = isSelected,
                        selectedColor = selectedColor,
                        defaultColor = defaultColor,
                        defaultRadius = defaultRadius,
                        selectedLength = selectedLength,
                        animationDurationInMillis = animationDurationInMillis,
                    )

                }
            }
        }
        var size = 30.dp;
        if (Logic.descriptionMap[Logic.descriptionNames[selectedPage]]!!.type ==
                Logic.DescriptionType.SCHEDULE) {
            size = 0.dp
        }
        Column(
            Modifier
                .padding(start = size, end = size, top = 60.dp)
        ) {
            println(descriptionMap[descriptionNames[selectedPage]]!!.type)
            println(descriptionNames[selectedPage])
            println(selectedPage)
            println(descriptionNames)
            val name = descriptionNames[selectedPage]!!
            val field = descriptionMap[name]!!
            val typo = field.type
            if (typo == Logic.DescriptionType.DATE) {
                DateQuestion(onError = changeEnabled, id = selectedPage)
            }
            else{
                Text(
                        style = MaterialTheme.typography.headlineMedium,
                        text = field.title,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp - size, end = 30.dp - size)
                    )

                Text(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .padding(start = 30.dp - size, end = 30.dp - size),
                    text = field.body,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray
                )
                if (typo == Logic.DescriptionType.STRING || typo == Logic.DescriptionType.NUMBER){
                    StringQuestion(id = selectedPage, onError = changeEnabled)
                } else if (typo == Logic.DescriptionType.CHECKBOX) {
                    SelectionQuestion(id = selectedPage)
                } else if (typo == Logic.DescriptionType.SCHEDULE) {
                    ScheduleQuestion(stateGrid)
                } else if (typo == Logic.DescriptionType.PHOTO) {
                    PhotoQuestion(id = selectedPage)
                }
            }
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    onClick = {
                        if (typo == Logic.DescriptionType.SCHEDULE) {
                            var toex : MutableMap<String, Boolean> = HashMap();
                            for (i in Logic.Schedule.keyNames) {
                                toex[i] = stateGrid.value[i]!!.value
                            }
                            Logic.descriptionStates["scheduleMap"] = toex
                            println(Logic.descriptionStates)
                        }
                        if (selectedPage != (numberOfPages - 1)) {
                            //TODO save data
                            onClick(selectedPage + 1);
                        } else {
                            //onFinish()
                        }
                    },
                    modifier = Modifier
                        .padding(
                            bottom = 30.dp, end = 30.dp
                        )
                        .clip(CircleShape)
                        .background(
                            if (isEnabledValue) {
                                Purple40
                            } else {
                                Color.Gray
                            }
                        ),
                    enabled = isEnabledValue
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow_bold_foreground),
                        contentDescription = "Next page UI questions",
                        modifier = Modifier.padding(10.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}




@Preview
@Composable
fun QuestionPage(

){
    val (selectedPage, setSelectedPage) = remember {
        mutableIntStateOf(0)
    }

    // NEVER use this, this is just for exampl

    PageIndicatorQuestions(
        selectedPage = selectedPage,
        defaultRadius = 10.dp,
        selectedLength = 40.dp,
        space = 2.dp,
        onClick = setSelectedPage,
        selectedColor = colorResource(id = R.color.purple_500),
        onFinish = {
            ProfileViewModel.uploadUser(AuthViewModel.getCurrentUser())
        }
    )

}

@Preview
@Composable
fun SelectionQuestion(
    id: Int = 4
) {
    val name = Logic.descriptionNames[id]!!
    val field = Logic.descriptionMap[name]!!
    var state by remember { mutableStateOf(true) }
    println(name)
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.selectableGroup(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = state,
                onCheckedChange = {
                    state = !state
                    if (state) {
                        Logic.descriptionStates[name] = 1
                    } else {
                        Logic.descriptionStates[name] = 0
                    }
                },

                )
            var text = ""
            text = if (state) {
                "Male"
            } else {
                "Female"
            }
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun DateQuestion(
    id: Int = 3,
    onError: (Boolean) -> Unit = {}
){


    val name = Logic.descriptionNames[id]!!
    val field = Logic.descriptionMap[name]!!
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        // Blocks Sunday and Saturday from being selected.
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val currentMillis = System.currentTimeMillis()

                // Convert milliseconds to LocalDateTime
                val currentTime = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(currentMillis),
                    ZoneOffset.UTC
                )



                // Subtract 18 years from the current time
                val result = currentTime.minusYears(18)


                // Convert LocalDateTime back to milliseconds
                val resultMillis = result.toInstant(ZoneOffset.UTC).toEpochMilli()
                return resultMillis >= utcTimeMillis
            }
            else {
                return true
            }
        }
    },
        initialDisplayMode = DisplayMode.Input
        )
    val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
    onError(confirmEnabled.value)

    DatePicker(title = { Text(text = field.title, style = MaterialTheme.typography.titleLarge)  }, headline = { Text(field.body, color = Color.Gray) }, state = datePickerState,showModeToggle = false, modifier = Modifier.padding(bottom = 40.dp))
    if (datePickerState.selectedDateMillis != null) {
        Logic.descriptionStates[name] = datePickerState.selectedDateMillis
        println(Logic.descriptionStates)
    }
}


@Composable
fun ScheduleQuestion(
    state : MutableState<HashMap<String, MutableState<Boolean>>>,
) {
    for (i in Logic.Schedule.keyNames) {
        state.value[i] = rememberSaveable{ mutableStateOf(false) }
    }
    ScheduleGrid(state = state, true)
    println("Change")
}

@Composable
fun PhotoQuestion(
    id: Int
) {
    val name = Logic.descriptionNames[id]!!
    val field = Logic.descriptionMap[name]!!
    var photoURL = rememberSaveable {mutableStateOf("")}

    var imageUriState = remember{mutableStateOf<Uri?> (null)}
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUriState.value = uri
        ProfileViewModel.tryUploadImage(photoURL, imageUriState)
        descriptionStates[name] = photoURL.value
    }
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(bottom = 40.dp),
        ) {
            Surface(
                onClick = {
                    getContent.launch("image/*")
                },
                modifier = Modifier.graphicsLayer {
                    clip = true
                    shape = CircleShape
                    translationY = 30.dp.toPx()
                }
            ) {
                LoadImageFromUrlExample(
                    imageUrl = photoURL.value,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.Start).background(Color.LightGray)

                )
            }
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = colorResource(id = R.color.purple_200),
                onClick = {
                    getContent.launch("image/*")
                }

            ) {
                Row() {

                    Icon(painterResource(R.drawable.photo_icon_foreground), "Change photo UI", modifier = Modifier
                        .padding(start = 10.dp)
                        .width(20.dp))
                    Text(text = "Upload", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(start = 5.dp, end = 10.dp))
                }
            }
        }
    }
}
