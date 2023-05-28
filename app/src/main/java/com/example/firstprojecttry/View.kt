package com.example.firstprojecttry

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController();
            val ve = remember{mutableStateOf(Integer.valueOf(0))};
            ve.value = 23;
            Scaffold(bottomBar = {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavItem(
                            name = "Search",
                            route = "search",
                            painter = painterResource(id = R.drawable.search)
                        ),
                        BottomNavItem(
                            name = "Messages",
                            route = "messages",
                            painter = painterResource(id = R.drawable.messages),
                            messagesCount = ve
                        ),
                        BottomNavItem(
                            name = "Profile",
                            route = "profile",
                            icon = Icons.Default.Person,
                        )
                    ),
                    navController = navController,

                    onItemClick = {
                        navController.navigate(it.route)
                    }

                )
            }) {
                    paddingValues ->  Box(modifier = Modifier.padding(paddingValues)) {
                Navigation(navController = navController)
            }
            }
        }
    }
}


@Composable
fun MyApp(navController: NavHostController) {
    NavHost(navController, startDestination = "screenProfile") {
        composable("screen1") {
            Screen1(navController)
        }
        composable("screen2") {
            var Loading = remember {mutableStateOf(false)}
            Screen2(navController, Loading)
        }
        composable("screen3") {
            Screen3(navController)
        }
        composable("screenProfile") {
            ScreenProfile()
        }
    }
}

@Composable
fun Screen1(navController: NavController) {
    LoadingBlock()
}

@Composable
fun Screen2(navController: NavController, Loading: MutableState<Boolean>) {
    ServerLogic.navController = navController;
    if (Loading.value) {
        Loading.value = false;
        navController.navigate("screen1");
    }
    /*
    var me : Logic.Executor = Logic.Executor();
    me.setId(53243);
    ServerLogic.addExecutor(me);
    Button(onClick = { navController.navigate("screen1") }) {
        Text("Go back to Screen 1")
    }

     */
}


@Composable
fun Screen3(navController: NavController) {

}








