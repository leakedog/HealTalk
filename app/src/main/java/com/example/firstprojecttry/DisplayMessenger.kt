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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.draw.drawBehind
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
import androidx.compose.ui.unit.max
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
import com.example.firstprojecttry.Logic.User
import com.google.firebase.concurrent.UiExecutor
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.TimeZone
import com.example.firstprojecttry.Messenger
import com.example.firstprojecttry.MessageModel.sendMessage
import com.example.firstprojecttry.Messenger.Chats
import com.example.firstprojecttry.Messenger.cpy
import com.example.firstprojecttry.Messenger.startCommunication
import com.example.firstprojecttry.uploadModel.navController
import com.google.android.gms.maps.model.Circle
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
public var recomposeShowChatScreen : () -> Unit = {};

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
    val listState = rememberLazyListState()






    System.out.println(getRoot(navController));
    System.out.println("ShowChatScreen");
    val state = rememberLazyListState()


    val coroutineScope = rememberCoroutineScope()

    var loaded by remember {mutableStateOf(false)}
    loaded = false;


    // System.out.println("Viwer ? + " + viewer.toString() + Logic.User.container.get(viewer).getName())
    //
    /// hm if somebody sends you a message what do you do?
    /// I suggest to store such a variable
    LaunchedEffect(loaded) {
        coroutineScope.launch {
            state.animateScrollToItem(
                index = kotlin.math.max(0, state.layoutInfo.totalItemsCount)
            )
        }
    }
    Column(/*modifier = Modifier.fillMaxHeight().fillMaxWidth().background(color = Color.Blue)*/
    )  {

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            User.getContainer().get(x.getOpposite(viewer)).name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {println("GOING BACK"); goBackFun()}) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            //TODO change here to user
                            ProfileViewModel.showExecutorFromChat(Executor.container.get(x.getOpposite(viewer)))
                        }) {
                            /* Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )*/
                            LoadImageFromUrlExample(
                                imageUrl = User.getContainer().get(
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

                val debug = false;
                if(debug) {
                    Column {
                        val messages: ArrayList<Messenger.Message>? = x.getMessages();

                        LazyColumn(state = state) {
                            items(100) {
                                Text(
                                    text = "$it",
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }
                            loaded = true;
                        }

                    }
                }else {
                    val messages: ArrayList<Messenger.Message>? = x.getMessages();

                    Column() {
                        Spacer(Modifier.height(8.dp))
                        var recompose1 by remember{mutableStateOf(0)}

                        recomposeShowChatScreen =  {
                            System.out.println("recomposeShowChatScreen");
                            recompose = 1 - recompose;
                            recompose1 = 1 - recompose1;
                        }

                        println("RECOMPOSE CHAT " + recompose + " " + messages?.size.toString());
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 110.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            state = state
                        ) {
                            if (messages != null) {
                                items(messages) { message ->
                                    if(recompose1 == 5){
                                        Text("hey")
                                    }
                                    val sender: Int = message.sender;
                                    val side: UserMessageSide =
                                        (if (sender == viewer) UserMessageSide.SENDER else UserMessageSide.RECEIVER);
                                    DisplayMessage(
                                        message.text,
                                        side,
                                        User.getContainer().get(sender).getPhoto()
                                    );
                                    if (message == messages[messages.size - 1]) {

                                        loaded = true;
                                    }
                                }

                            }

                        }
                        Spacer(Modifier.height(50.dp))
                    }

                }
            },
            bottomBar = {
                var text by rememberSaveable { mutableStateOf("") }
                var showSuggest by rememberSaveable { mutableStateOf(false) }
                var buttonClick by rememberSaveable { mutableStateOf(false) }
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(Modifier.height((getHeight() - 100).dp).background(color = Color.Green))

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
                                    loaded = !loaded;
                                    text = ""
                                    recompose = 1 - recompose
                                }, modifier = Modifier
                                    .width(50.dp)
                                    .height(50.dp)
                                    .clip(
                                        CircleShape
                                    )



                            ) {

                                Icon(
                                    painter = painterResource(R.drawable.send_button),
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
    System.out.println("hey hey hey\n");
    navController.navigate("chat");
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

var debug = true;
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowChats(user : User){
    val userId : Int = user.getId()


    val allChats = Messenger.userChat.get(userId)

    var showSpecificChat by rememberSaveable{mutableStateOf(false)}
    var chatToShow by remember {mutableStateOf(Messenger.Chat())}
    val backStackEntry = navController.currentBackStackEntryAsState()
    val toDo : () -> Unit =  {navController.navigate("chat");}


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
            System.out.println("We are here\n");
            if(allChats == null || allChats.isEmpty()){
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    Text("No messages", style = MaterialTheme.typography.titleMedium)
                }
            } else {
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(allChats) { chat ->
                        val op: User = User.getContainer().get(
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
                                // chatToShow = Messenger.Chats.get(chat)
                                navController.navigate("chats/"+op.id);

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

@Preview
@Composable
fun checkout(){
    bottom()
}
/// DisplayMessage(text, receiver?0:1, pictureMe)
///

