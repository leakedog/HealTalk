package com.example.firstprojecttry

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstprojecttry.ui.theme.FirstProjectTryTheme
import kotlin.text.Typography.times
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.RectangleShape
import com.example.firstprojecttry.Logic.Executor


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            FirstProjectTryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExecutorCard(executor = Logic.Executor(0, "Bogdan", Logic.Description(), Logic.Schedule(), 20, Logic.Photo(), Logic.Side.BOTH, "Krakow"))
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExecutorCard(executor: Executor) {

    FirstProjectTryTheme {
        val visibleState = remember { mutableStateOf(true) }
        var visible by remember { visibleState }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AnimatedVisibility( // Анимирование видимости Box
                visible = visible,
                enter = slideInHorizontally(animationSpec = tween(durationMillis = 200)) { fullWidth ->
                    // Offsets the content by 1/3 of its width to the left, and slide towards right
                    // Overwrites the default animation with tween for this slide animation.
                    -fullWidth / 3
                } + fadeIn(
                    // Overwrites the default animation with tween
                    animationSpec = tween(durationMillis = 200)
                ),
                exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
                    // Overwrites the ending position of the slide-out to 200 (pixels) to the right
                    200
                } + fadeOut()
            ) {
                Scaffold(
                    topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Profile",
                                        fontSize = 25.sp,
                                        )
                                        },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        closeExecutorCard(visible = visibleState)
                                    }) {
                                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                                    }

                                }
                            )
                        },

                    content = { contentPadding ->
                        // Screen content
                        Box(modifier = Modifier.padding(contentPadding)) {
                            ExecutorCardInfo(executor = executor)
                        }
                    }

                )
            }
        }
    }


}

@Composable
fun createTitleLargeText(text: String, paddingValues: PaddingValues, theme: MaterialTheme) {
    Text(
        text = text,
        color = theme.colorScheme.primary,
        modifier = Modifier.padding(paddingValues),
        style = theme.typography.titleLarge,
    )
}
@Composable
fun createTitleMediumText(text: String, paddingValues: PaddingValues, theme: MaterialTheme) {
    Text(
        text = text,
        color = theme.colorScheme.secondary,
        modifier = Modifier.padding(paddingValues),
        style = theme.typography.titleMedium,
    )
}
@Composable
fun createTitleInfoText(text: String, paddingValues: PaddingValues, theme: MaterialTheme) {
    Text(
        text = text,
        color = theme.colorScheme.primary,
        modifier = Modifier.padding(paddingValues),
        style = theme.typography.titleMedium,
    )
}
@Composable
fun createProgressBar(value: Float, outOf: Float, color: Color) {
    LinearProgressIndicator(
        progress = value / outOf,
        color = color,
        modifier = Modifier
            .width(200.dp)
            .padding(horizontal = 16.dp, vertical = 3.dp)
            .height(20.dp)
            .clip(RoundedCornerShape(50))
    )
}


@Composable
fun ExecutorCardInfo(executor: Executor) {
    FirstProjectTryTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                createTitleLargeText(
                    text = executor.name,
                    paddingValues = PaddingValues(start = 14.dp),
                    theme = MaterialTheme
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(Modifier.width(500.dp)) {
                    Image(
                        painter = painterResource(R.drawable.profile_picture),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                            .align(Alignment.TopStart)
                    )
                    Column(modifier = Modifier.align(Alignment.TopEnd)) {
                        createTitleMediumText(
                            text = "Price - " + executor.price.toString() + " rubles",
                            paddingValues = PaddingValues(start = 22.dp),
                            theme = MaterialTheme
                        )
                        createProgressBar(
                            value = executor.price.toFloat(),
                            outOf = 30f,
                            color = Color.Green
                        )
                        createTitleMediumText(
                            text = "Rating - " + (executor.rating as? Float
                                ?: 0f).toString(),
                            paddingValues = PaddingValues(start = 22.dp, top = 10.dp),
                            theme = MaterialTheme
                        )
                        createProgressBar(
                            value = executor.rating.grade.toFloat(),
                            outOf = 10f,
                            color = Color.Green
                        )
                    }

                }
                createTitleInfoText(
                    text = "About me",
                    paddingValues = PaddingValues(start = 14.dp),
                    theme = MaterialTheme
                )
                Text(
                    text = executor.description.text,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 14.dp)
                )
                Text(
                    text = "When works",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 14.dp)
                )
                ColorGrid()
            }
            Button(
                onClick = {
                    ServerLogic.addExecutor(executor)
                }, modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
                    .width(200.dp)
            ) {
                Text(
                    text = "Order",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
@Composable
fun ColorGrid() {
    LazyHorizontalGrid(rows = GridCells.Adaptive(35.dp),
        ) {
        items(listOf(-1, 0, 1, 2, 3)) { index ->
            Row (){
                val time = when (index) {
                    0 -> "Morning"
                    1 -> "Afternoon"
                    2 -> "Evening"
                    3 -> "Night"
                    else -> ""
                }
                Box (modifier = Modifier.width(130.dp)) {
                    Text(
                        text = time,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }
                for (i in 1..7) {

                    Box(Modifier.padding(start = 5.dp, end = 5.dp, top = 7.dp)) {
                        if (index < 0) {
                            val day = when (i) {
                                1 -> "Mo"
                                2 -> "Tu"
                                3 -> "We"
                                4 -> "Th"
                                5 -> "Fr"
                                6 -> "Sa"
                                7 -> "Su"
                                else -> ""
                            }
                            Surface(
                                shape = RectangleShape,
                                modifier = Modifier
                                    .animateContentSize()
                                    .padding(1.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(25.dp)
                                        .width(25.dp)
                                        .height(25.dp)
                                ) {
                                    Text(
                                        text = day,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 17.sp,
                                    )
                                }
                            }
                        } else {
                            var isChanged by remember { mutableStateOf(false) }
                            // surfaceColor will be updated gradually from one color to the other
                            val surfaceColor by animateColorAsState(
                                if (isChanged) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                            )
                            var borderColor by remember { mutableStateOf(Color.Black) } // track border color
                            Surface(
                                shape = RectangleShape,
                                // surfaceColor color will be changing gradually from primary to surface
                                color = surfaceColor,
                                // animateContentSize will change the Surface size gradually
                                modifier = Modifier
                                    .animateContentSize()
                                    .padding(1.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(25.dp)
                                        .border(width = 1.dp, color = borderColor)
                                        .clickable {
                                            isChanged = !isChanged
                                            borderColor =
                                                if (borderColor == Color.Black) Color.White else Color.Black // toggle border color on click

                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun closeExecutorCard(visible: MutableState<Boolean>) {
    visible.value = !visible.value;
}

/*
@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {

        Spacer(modifier = Modifier.width(10.dp))
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall

            )

            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp, color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium

                )
            }


        }

    }
}
*/
@Preview
@Composable
fun PreviewExecutorCard() {
    FirstProjectTryTheme {
        Surface {
            ExecutorCard(
                executor = Logic.Executor(
                    0,
                    "Bogdan",
                    Logic.Description(),
                    Logic.Schedule(),
                    20,
                    Logic.Photo(),
                    Logic.Side.BOTH,
                    "Krakow"
                )
            )
        }
    }

}




/*
@Preview (name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)


@Composable
fun PreviewMessageCardE() {
    FirstProjectTryTheme {
        Surface {
            MessageCard(
                msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }

}


@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    FirstProjectTryTheme {
        Conversation(SampleData.conversationSample)
    }
}*/