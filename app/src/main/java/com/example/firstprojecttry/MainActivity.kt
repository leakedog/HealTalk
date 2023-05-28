package com.example.firstprojecttry

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.firstprojecttry.Logic.Executor
import com.example.firstprojecttry.ui.theme.FirstProjectTryTheme
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState


class rl : ComponentActivity() {
    private var isRenderCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            LoadingBlock()
        }
        /*
        setContent {
            // A surface container using the 'background' color from the theme
            FirstProjectTryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val center = LatLng(50.0614285,19.9209253)
                    val centerState = MarkerState(position = center)
                    val cameraPositionState = rememberCameraPositionState{
                        position = CameraPosition.fromLatLngZoom(center, 17f)
                    }
                    Box {
                        var showId by remember {mutableStateOf(Executor())}
                        /*showId =         Logic.Executor(
                            2,
                            "Maria Leverbook",
                            Logic.Description("I have over 14 years of experience working as a nanny, I love and enjoy to work as a Baby care giver, part of my duties are; prepare their food, change diapers, get them ready for naps, entertainment for the kids, teaching them to sit up, crawl and walk, I also socialize them by taking them to the parks to play or have a play date, I'll read books to them, teach Spanish with the parents approval, I change beds, do laundry for the babies. I also clean whatever I use in the kitchen after I prepare food for the babies. My first job as a nanny was in New York, I was taking care of twins babies girls. Then I moved to Georgia 13 years ago, during those years I was a care giver for babies in different families. Recently we moved to Menifee, CA on February Due to my husband's job in Aviation, he was transferred to California permanently, reference are available upon request."),
                            Logic.Schedule(),
                            22,
                            Logic.Photo(),
                            Logic.Side.CLIENTHOME,
                            "Poland, Krakow, ul. Huka 3"
                        )*/
                        var showExecutor = remember {mutableStateOf(false)}
                        val displayCard = {a: Executor -> showId = a
                                                            showExecutor.value = true
                                                                        Unit}
                        var showMessages = remember{mutableStateOf(false)}



                        GoogleMap(
                            modifier = Modifier.fillMaxSize(),
                            cameraPositionState = cameraPositionState
                        ) {

                            var sample = listOf<LatLng>(LatLng(50.0600659,19.921837), LatLng(50.059785, 19.923739), LatLng(50.0614285,19.9209253), LatLng(50.0597667,19.9201786), LatLng(50.065081, 19.923790), LatLng(50.058137, 19.924342), LatLng(50.061567, 19.926101))

                            val executors = listOf<Executor>(
                                Logic.Executor(
                                    4,
                                    "Elizabeth Mitchi",
                                    Logic.Description("I am a college graduate from SUNY Cortland. I have a degree in Sociology and Spanish, with a minor in Latin American Studies. I am in the process of pursuing a Masters in Social Work. I am also a Spanish tutor."
                                    ),
                                    Logic.Schedule(),
                                    30,
                                    Logic.Photo(),
                                    Logic.Side.EXECUTORHOME,
                                    Logic.Location(50.0600659,19.921837)
                                ),
                                Logic.Executor(
                                    3,
                                    "Tiffany Villet",
                                    Logic.Description("Hello. My name is Alan and I am 29 years old. I am an organized, clean, easygoing, loving, patient and fun person. I am a strong driver with a reliable car, non-smoker, fully vaccinated (Covid, Tdap and Flu), CPR certified and pet friendly. I have a teaching degree in Brazil to be a teacher of Portuguese and English and right after college I got a lot of experience as a tutor"),
                                    Logic.Schedule(),
                                    19,
                                    Logic.Photo(),
                                    Logic.Side.BOTH,
                                    Logic.Location(50.059785, 19.923739)
                                ),
                                Logic.Executor(
                                    2,
                                    "Maria Leverbook",
                                    Logic.Description("I have over 14 years of experience working as a nanny, I love and enjoy to work as a Baby care giver, part of my duties are; prepare their food, change diapers, get them ready for naps, entertainment for the kids, teaching them to sit up, crawl and walk, I also socialize them by taking them to the parks to play or have a play date, I'll read books to them, teach Spanish with the parents approval"),
                                    Logic.Schedule(),
                                    22,
                                    Logic.Photo(),
                                    Logic.Side.CLIENTHOME,
                                    Logic.Location(50.0614285, 19.9209253)
                                ),
                                Logic.Executor(
                                    4,
                                    "Elizabeth Mitchi",
                                    Logic.Description("I am a college graduate from SUNY Cortland. I have a degree in Sociology and Spanish, with a minor in Latin American Studies. I am in the process of pursuing a Masters in Social Work. I am also a Spanish tutor."
                                    ),
                                    Logic.Schedule(),
                                    30,
                                    Logic.Photo(),
                                    Logic.Side.EXECUTORHOME,
                                    Logic.Location(50.065081, 19.923790)
                                ), Logic.Executor(
                                    3,
                                    "Tiffany Villet",
                                    Logic.Description("Hello. My name is Alan and I am 29 years old. I am an organized, clean, easygoing, loving, patient and fun person. I am a strong driver with a reliable car, non-smoker, fully vaccinated (Covid, Tdap and Flu), CPR certified and pet friendly. I have a teaching degree in Brazil to be a teacher of Portuguese and English and right after college I got a lot of experience as a tutor"),
                                    Logic.Schedule(),
                                    19,
                                    Logic.Photo(),
                                    Logic.Side.BOTH,
                                    Logic.Location(50.058137, 19.924342)
                                ), Logic.Executor(
                                    2,
                                    "Maria Leverbook",
                                    Logic.Description("I have over 14 years of experience working as a nanny, I love and enjoy to work as a Baby care giver, part of my duties are; prepare their food, change diapers, get them ready for naps, entertainment for the kids, teaching them to sit up, crawl and walk, I also socialize them by taking them to the parks to play or have a play date, I'll read books to them, teach Spanish with the parents approval"),
                                    Logic.Schedule(),
                                    22,
                                    Logic.Photo(),
                                    Logic.Side.CLIENTHOME,
                                    Logic.Location(50.061567, 19.926101)
                                )


                            )

                            DisplayAllMarkers(executors, displayCard)

                            /*
                        Marker(
                            state = singaporeState,
                            title = "Marker in Singapore"
                        )*/
                        }
                        if(showMessages.value == true){
                            Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
                                Conversation(SampleData.conversationSample)
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {
                                             showMessages.value = !showMessages.value
                            }, modifier = Modifier.padding(20.dp).height(50.dp).width(300.dp))
                            { Text("Message") }
                        }
                        //modifier = Modifier.size(10.dp).background(Color.White)
                        if(showExecutor.value == true){
                            Log.d("showExecutor", "true")
                            ExecutorCard(executor = showId, showExecutor)
                        // Text("Hello", style = TextStyle(fontSize = 50.sp))
                        }else{
                            Log.d("showExecutor", "false");
                        }

                    }

                    //ExecutorCard(executor = Logic.Executor(0, "Bogdan", Logic.Description(), Logic.Schedule(), 20, Logic.Photo(), Logic.Side.BOTH, "Krakow"))

                }
            }
        }

         */
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "WOW")
    }
    override fun onResume() {
        super.onResume()

        // Проверьте флаг успешного завершения рендеринга в onResume()
        if (isRenderCompleted) {
            System.out.println("LODAOD")
            // Рендеринг успешно завершен, выполните необходимые действия
        }
    }
}
data class Message(val author: String, val body: String)
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message("Client", "Hi, are you available for babysitting on Friday evening?"),
        Message("Babysitter", "Yes, I'm available on Friday evening. What time do you need me?"),
        Message("Client", "We would need you from 7 PM to 11 PM. Can you come to our place?"),
        Message("Babysitter", "Sure, I can come to your place. Could you please provide me with the address?"),
        Message("Client", "The address is 123 Main Street. Looking forward to having you babysit our kids!"),
        Message("Babysitter", "Thank you! I'll be there at 7 PM on Friday. Is there anything specific I should know or bring?"),
        Message("Client", "Our kids are 3 and 5 years old. They usually have dinner around 6 PM, so they might be hungry. It would be great if you could bring some snacks for them."),
        Message("Babysitter", "Noted. I'll bring some snacks for them. If there's anything else, please let me know."),
        Message("Client", "Will do. Thank you for your help! See you on Friday!"),
        Message("Babysitter", "You're welcome! See you on Friday!")
    )
}
@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.elizabet),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
            ) {
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

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}
@Composable
fun getBitMap(
    context: Context,
    @DrawableRes iconResourceId: Int
): BitmapDescriptor? {
    val icon = bitmapDescriptorFromVector(context, iconResourceId)
    return icon
}
@Composable
fun DisplayAllMarkers(marks: List<Executor>, updateStatus: (Executor) -> Unit){
    var id = 0
   // var sample = listOf<LatLng>(LatLng(50.0600659,19.921837), LatLng(50.059785, 19.923739), LatLng(50.0614285,19.9209253), LatLng(50.0597667,19.9201786), LatLng(50.065081, 19.923790), LatLng(50.058137, 19.924342), LatLng(50.061567, 19.926101))
    /*val bitmap = BitmapFactory.decodeResource(
        LocalContext.current.resources,
        R.drawable.marksitter
    )*/
    for(executor in marks){
        var checkedState by remember {mutableStateOf(false)}
        if(checkedState == true){
            updateStatus(executor)
            checkedState = false
        }
        Marker(
            state = MarkerState(position = LatLng(executor.getLocation().lat, executor.getLocation().lng)),
            onClick = {mar ->
                checkedState = true
                true
            },
            icon = getBitMap(LocalContext.current, R.drawable.smaller)
        )
        id++
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExecutorCard(executor: Executor, visible: MutableState<Boolean>) {

    FirstProjectTryTheme {


        AnimatedVisibility(visible = visible.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                AnimatedVisibility( // Анимирование видимости Box
                    visible = visible.value,
                    enter = slideInHorizontally(animationSpec = tween(durationMillis = 20)) { fullWidth ->
                        // Offsets the content by 1/3 of its width to the left, and slide towards right
                        // Overwrites the default animation with tween for this slide animation.
                        -fullWidth / 3
                    } + fadeIn(
                        // Overwrites the default animation with tween
                        animationSpec = tween(durationMillis = 20)
                    ),
                    exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
                        // Overwrites the ending position of the slide-out to 200 (pixels) to the right
                        20
                    } + fadeOut()
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Profile",
                                        fontSize = 25.sp,
                                    )
                                },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        closeExecutorCard(visible = visible)
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
                        painter = painterResource(
                            if(executor.name[0] == 'E'){
                            R.drawable.elizabet
                            }else{
                                 if(executor.name[0] == 'M'){
                                     R.drawable.john
                                 }else{
                                    R.drawable.jack
                                 }
                            }),
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
                    text = "executor.description.text",
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
    LazyHorizontalGrid(
        rows = GridCells.Adaptive(35.dp),
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