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
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MediumTopAppBar
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
import com.example.firstprojecttry.Messenger
import com.example.firstprojecttry.Logic
import com.example.firstprojecttry.MessageModel.sendMessage
import com.example.firstprojecttry.Messenger.Chats
import com.example.firstprojecttry.Messenger.cpy
import com.example.firstprojecttry.uploadModel.navController
import com.google.android.gms.maps.model.Circle
import com.google.firebase.firestore.auth.User
import java.lang.Thread.sleep
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import kotlin.math.min

enum class UserMessageSide{
    SENDER,
    RECEIVER
}
/*
@Composable
fun DisplayMessages(x: Messenger.Chat, Viewer: Logic.User, mod : Modifier){

        val messages: ArrayList<Messenger.Message>? = x.getMessages();
        if(messages != null) {
            items(messages) { message ->
                val sender: Logic.User = message.getSender();
                val side: UserMessageSide =
                    (if (sender == Viewer) UserMessageSide.SENDER else UserMessageSide.RECEIVER);
                DisplayMessage(message.getText(), side, sender.getPhoto());

            }
        }
    }
*/


@Composable
fun getHeight() : Int{
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    return screenHeight
}
@Composable
fun getWidth() : Int{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    return screenWidth
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowChatScreen(x : Messenger.Chat, viewer: Int, goBackFun : () -> Unit){
   /* val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = imeState.value){
        if(imeState.value) {
            scrollState.scrollTo(scrollState.maxValue)
        }
    }*/


    var recompose by rememberSaveable { mutableStateOf(0) }

        println("RECOMPOSE CHAT " + recompose)
        //System.out.println("Viwer ? + " + viewer.toString() + Logic.User.container.get(viewer).getName())
        //
        /// hm if somebody sends you a message what do you do?
        /// I suggest to store such a variable
        Column(/*modifier = Modifier.fillMaxHeight().fillMaxWidth().background(color = Color.Blue)*/
        )  {

            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                Logic.User.container.get(x.getOpposite(viewer)).getName(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { goBackFun()}) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                //TODO change here to user
                                ProfileViewModel.showExecutorFromChat(Logic.Executor.container.get(x.getOpposite(viewer)))
                            }) {
                                /* Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Localized description"
                                )*/
                                LoadImageFromUrlExample(
                                    imageUrl = Logic.User.container.get(
                                        x.getOpposite(
                                            viewer
                                        )
                                    ).getPhoto().getPhotoURL(), modifier = Modifier
                                        .size(30.dp)
                                        .clip(
                                            CircleShape
                                        )
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = messageBackView)
                    )
                },
                content = { innerPadding ->

                    Box {

                        Column() {
                            Spacer(Modifier.height(8.dp))
                            LazyColumn(
                                contentPadding = innerPadding,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val messages: ArrayList<Messenger.Message>? = x.getMessages();
                                if (messages != null) {
                                    items(messages) { message ->

                                        val sender: Int = message.sender;
                                        val side: UserMessageSide =
                                            (if (sender == viewer) UserMessageSide.SENDER else UserMessageSide.RECEIVER);
                                        DisplayMessage(
                                            message.text,
                                            side,
                                            Logic.User.container.get(sender).getPhoto()
                                        );

                                    }
                                }

                            }
                            Spacer(Modifier.height(80.dp))
                        }

                    }
                },
                bottomBar = {
                    var text by rememberSaveable { mutableStateOf("") }
                    var showSuggest by rememberSaveable { mutableStateOf(false) }
                    var buttonClick by rememberSaveable { mutableStateOf(false) }
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(Modifier.height((getHeight() - 100).dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                        ) {
                            Spacer(
                                Modifier
                                    .width(10.dp)
                            )
                            TextField(
                                value = text,
                                onValueChange = {
                                    text = it
                                },
                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(getWidth().dp - 80.dp),
                                placeholder = { Text(text = "Type a message") },
                                textStyle = messageFont

                            )

                            Spacer(
                                Modifier
                                    .width(10.dp)
                            )
                            Column(modifier = Modifier.fillMaxSize()) {
                                Spacer(Modifier.height(5.dp))
                                IconButton(
                                    onClick = {
                                        buttonClick = !buttonClick
                                        sendMessage(
                                            x,
                                            Messenger.Message(
                                                text,
                                                "Today",
                                                x.getId(),
                                                viewer
                                            )
                                        );
                                        text = ""
                                        recompose = 1 - recompose
                                    }, modifier = Modifier
                                        .width(50.dp)
                                        .height(50.dp)
                                        .background(Color.White)
                                        .clip(
                                            CircleShape
                                        )
                                        .border(2.dp, messageBackView)


                                ) {

                                    Icon(
                                        painter = painterResource(R.drawable.right_arrow),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(50.dp)
                                            .height(50.dp)
                                    )
                                }

                            }

                        }

                        Spacer(
                            Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                                .background(Color(0xFFf2f2f2)))
                    }
                }
            )
        }
}

@Composable
fun check(x: MutableState<Int>) {
    sleep(200)
    x.value += 1



    // Display the value of x and name using the Text composable

    Text("Number is ${x.value}")
}
@Composable
fun check1() {
    sleep(200)
    var num by rememberSaveable {mutableStateOf(1)}
    Text("Num " + num);
    num++;
}
@Composable
fun Counter(x: Messenger.Chat, viewer: Int) {
    System.out.println("start of counter " + x.getMessages().size);
    val recompose = remember { mutableStateOf(0) }

    Button(onClick = {
         MessageModel.sendMessage(
            x,
            Messenger.Message(
                "I'm high today",
                "Today",
                x.getId(),
                viewer
            )
        )

        System.out.println(x.getMessages().size)
        recompose.value++
    }) {
        Text("Increment " + recompose.value)
    }

    sleep(1000)
    Text("Count: ${x.getMessages().size} hm ${recompose.value}")
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun demonstrate(){

    val executor: Logic.Executor = Logic.Executor.container.getFirst();
    val client: Logic.Executor = Logic.Executor.container.get(53243);
    val chat: Messenger.Chat = Messenger.startCommunication(client.getId(), executor.getId())
  //  uploadModel.addChat(chat);
    var va: MutableState<Messenger.Chat> = remember { mutableStateOf(chat) }
    System.out.println(chat.getId().toString()+ " A: " + chat.getA().toString() + " B: " + chat.getB().toString());
    assert((chat.getA() == client.getId() || chat.getA() == executor.getId())
            && (chat.getB() == client.getId() || chat.getB() == executor.getId()));
   // ShowChatScreen(chat, executor.getId())
    ShowChats(executor)
}

@Composable
fun bottom(){
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height((getHeight() - 100).dp))
        var text = "5"
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(50.dp)) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                modifier = Modifier
                    .height(80.dp)
                    .width(getWidth().dp - 80.dp),
                placeholder = { Text(text = "Type a message") },
                textStyle = messageFont

            )
            Button(
                onClick = {

                }, modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)


            ) {
                Image(
                    painter = painterResource(R.drawable.send_button),
                    contentDescription = null,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
            }
        }
        Spacer(Modifier.height(20.dp))
    }
}
fun shortMessage(chat : Messenger.Chat, viewer : Int) : String{
    var text : String;
    val msg : Messenger.Message? = chat.getLastMessage()
    if(msg == null){
        text = "No messages yet..."
    }else{
        if(msg.sender == viewer){
            text = "Y: " + msg.getText()
        }else{
            text = msg.getText()
        }
    }
    return text.substring(0, min(text.length, 20)) + "..."
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowChats(user : Logic.User){
    val userId : Int = user.getId()
    val allChats = Messenger.userChat.get(userId)

    var showSpecificChat by rememberSaveable{mutableStateOf(false)}
    var chatToShow by remember {mutableStateOf(Messenger.Chat())}
    val backStackEntry = navController.currentBackStackEntryAsState()
    println("DEBUG: " + backStackEntry.value)
    if(showSpecificChat == true){
        val toDo : () -> Unit =  {showSpecificChat = false}
        ShowChatScreen(chatToShow, user.getId(), toDo)
    }else {
        Scaffold(
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text(
                            "Chats",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = messageBackView)
                )
            },
            content = { innerPadding ->
                if(allChats.isEmpty()){
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                        Text("No messages", style = MaterialTheme.typography.titleMedium)
                    }
                } else {
                    LazyColumn(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(allChats) { chat ->
                            val op: Logic.User = Logic.User.container.get(
                                Messenger.Chats.container.get(chat)!!.getOpposite(user.getId())
                            )
                            ListItem(
                                headlineContent = {
                                    Text(
                                        text = op.getName(),
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                },
                                leadingContent = {
                                    Column() {
                                        LoadImageFromUrlExample(
                                            imageUrl = op.getPhoto().getPhotoURL(),
                                            modifier = Modifier
                                                .size(70.dp)
                                                .clip(
                                                    CircleShape
                                                )
                                        )
                                    }

                                },
                                supportingContent = {
                                    /// last Message
                                    Text(
                                        shortMessage(
                                            Messenger.Chats.container.get(chat)!!,
                                            user.getId()
                                        )
                                    )
                                },
                                modifier = Modifier.clickable(onClick = {
                                    chatToShow = Messenger.Chats.get(chat)
                                    showSpecificChat = true
                                })
                            )
                        }
                    }
                }
            },
            bottomBar = {
                BottomBar()
            }
        )
    }
}
@Preview
@Composable
fun checkout(){
    bottom()
}
/// DisplayMessage(text, receiver?0:1, pictureMe)
///

