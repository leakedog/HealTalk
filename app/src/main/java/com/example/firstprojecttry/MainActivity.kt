package com.example.firstprojecttry

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.ui.theme.FirstProjectTryTheme


class MainActivity : ComponentActivity() {
    private lateinit var btnSave : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            FirstProjectTryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    TutorCard(tutor = Logic.Executor(0, "Bogdan", Logic.Description(), Logic.Schedule(), 20, Logic.Photo(), Logic.Side.BOTH, "Krakow"))
                }
            }

        }

    }
}



@Composable
fun TutorCard(tutor: Logic.Executor) {

    FirstProjectTryTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = tutor.name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 14.dp)
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
                        Text(
                            text = "Price - " + tutor.price.toString() + " rubles",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(
                                start = 22.dp
                            )
                        )
                        LinearProgressIndicator(
                            progress = tutor.price.toFloat() / 30f,
                            color = Color.Green,
                            modifier = Modifier
                                .width(200.dp)
                                .padding(horizontal = 16.dp, vertical = 3.dp)
                                .height(20.dp)
                                .clip(RoundedCornerShape(50))
                        )

                        Text(
                            text = "Rating - " + (tutor.rating as? Float ?: 0f).toString(),
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(
                                start = 22.dp, top = 10.dp
                            )
                        )
                        LinearProgressIndicator(
                            progress = tutor.rating.grade.toFloat() / 10f,
                            color = Color.Yellow,
                            modifier = Modifier
                                .width(200.dp)
                                .padding(horizontal = 16.dp, vertical = 3.dp)
                                .height(20.dp)
                                .clip(RoundedCornerShape(50))
                        )

                    }
                }
                Text(
                    text = "About me",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 14.dp)
                )
                Text(
                    text = tutor.description.text,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 14.dp)
                )


            }
            Button(onClick = {

            }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .width(200.dp)){
                Text (
                    text = "Order",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }

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
fun PreviewTutorCard() {
    FirstProjectTryTheme {
        Surface {
            TutorCard(tutor = Logic.Executor(0, "Bogdan", Logic.Description(), Logic.Schedule(), 20, Logic.Photo(), Logic.Side.BOTH, "Krakow"))
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