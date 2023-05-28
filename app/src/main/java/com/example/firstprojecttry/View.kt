package com.example.firstprojecttry

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*
            uploadModel.addClient(
                Logic.Client(
                    "DOESNT.. PAY OFF",
                    Logic.Description(),
                    1,
                    Logic.Side.EXECUTORHOME,
                    "fuk",
                    "35138517"
                )
            );
            uploadModel.addClient(
                Logic.Client(
                    "HardWORK",
                    Logic.Description(),
                    1,
                    Logic.Side.EXECUTORHOME,
                    "fuk",
                    "857192538.13512"
                )
            );
            uploadModel.addClient(
                Logic.Client(
                    "Vasya",
                    Logic.Description(),
                    1,
                    Logic.Side.EXECUTORHOME,
                    "fuk",
                    "97518.325"
                )
            );

            */
            val navController = rememberNavController()
            AuthViewModel.navController = navController;
            ProfileViewModel.navController = navController;
            MyApp(navController)
            AuthModel.startApplication()
        }
    }
}


@Composable
fun MyApp(navController: NavHostController) {
    NavHost(navController, startDestination = "start") {
        composable("error") {
            ErrorScreen()
        }
        composable("loading") {
            LoadingBlock()
        }
        navigation(startDestination = "greeting", route = "start") {
            composable("greeting") {
                GreetingScreen(navController)
            }
            composable("login") {
                LoginScreen(navController)
            }
            composable("registration") {
                SignUpScreen(navController)
            }
           
        }
        navigation(startDestination = "profile", route = "loggedApp") {
            composable("profile") {
                PreviewProfilePage()
            }

        }
        
        
    }
}

@Composable
fun Screen1(navController: NavController) {
    LoadingBlock()
}

@Composable
fun Screen2(navController: NavController, Loading: MutableState<Boolean>) {
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
    Text("Screen3")
}






@Composable
fun Screen4(navController: NavController) {
    Text("Screen4")
}




