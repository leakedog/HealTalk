package com.example.firstprojecttry

import ExecutorCard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.firstprojecttry.GoogleMap.ShowMap
import com.example.firstprojecttry.Logic.Executor
import com.example.firstprojecttry.Logic.UtilityClass
import com.example.firstprojecttry.Login.AuthModel
import com.example.firstprojecttry.Login.AuthViewModel
import com.example.firstprojecttry.Login.ChooseTypePage
import com.example.firstprojecttry.Login.ClientQuestionPage
import com.example.firstprojecttry.Login.ExecutorQuestionPage
import com.example.firstprojecttry.Login.ForgotPasswordScreen
import com.example.firstprojecttry.Login.GreetingScreen
import com.example.firstprojecttry.Login.LoginScreen
import com.example.firstprojecttry.Login.SignUpScreen
import com.example.firstprojecttry.Messenger.Messenger
import com.example.firstprojecttry.Messenger.ShowChatScreen
import com.example.firstprojecttry.Messenger.ShowChats
import com.example.firstprojecttry.Messenger.demonstrate
import com.example.firstprojecttry.Navigator.NavigatorModel
import com.example.firstprojecttry.Notifications.Notifications
import com.example.firstprojecttry.Profile.PreviewProfilePage
import com.example.firstprojecttry.Profile.ProfileViewModel
import com.example.firstprojecttry.Upload.uploadModel
import com.example.firstprojecttry.helperActivities.ErrorScreen
import com.example.firstprojecttry.helperActivities.LoadingBlock


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                } else -> {
                // No location access granted.
            }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION))
        setContent {

            val navController = rememberNavController()
            NavigatorModel.navController = navController;
            MyApp(navController)
            AuthModel.startApplication()
        }
    }

}


@Composable
fun MyApp(navController: NavHostController) {

    uploadModel()
    UtilityClass()
    NavHost(navController, startDestination = "loading") {
        composable("error") {
            ErrorScreen()
        }
        composable("loading") {
            LoadingBlock()
        }
        composable("demontt"){
            demonstrate()
        }

        navigation(startDestination = "greeting", route = "start") {
            composable("greeting") {
                GreetingScreen()
            }
            composable("login") {
                LoginScreen()
            }
            composable("registration") {
                SignUpScreen()
            }
            composable("forgotPassword"){
                ForgotPasswordScreen()
            }

        }
        navigation(startDestination = "profile", route = "loggedApp") {
            composable("profile") {
                PreviewProfilePage()
            }


        }
        navigation(startDestination = "chat", route = "messages"){
            composable("chat"){
                ShowChats(AuthModel.getCurrentUser());
            }
        }

        composable("search") {
            ShowMap()
        }
        composable("RegisterAsExecutor") {
            ExecutorQuestionPage()
        }
        composable("RegisterAsClient") {
            ClientQuestionPage()
        }
        composable("chooseTypeClientOrExecutor") {
            ChooseTypePage();
        }
        composable("executors/{userId}",   arguments = listOf(navArgument("userId") { type = NavType.IntType })) {
            val backStackEntry = navController.currentBackStackEntry!!;
            val executor = remember{mutableStateOf(Executor.container.get(backStackEntry.arguments!!.getInt("userId")))}
            ExecutorCard(executor = executor, onGoBack = {
                navController.navigate("search")
            })
        }
        composable("executorsFromChat/{userId}",   arguments = listOf(navArgument("userId") { type = NavType.IntType })) {
            val backStackEntry = navController.currentBackStackEntry!!;
            val userId = backStackEntry.arguments!!.getInt("userId")
            val executor = remember{mutableStateOf(Executor.container.get(backStackEntry.arguments!!.getInt("userId")))}
            ExecutorCard(executor = executor, onGoBack = {
                navController.navigate("chats/$userId")
            }, fromChat = true)
        }
        composable("chats/{userId}",   arguments = listOf(navArgument("userId") { type = NavType.IntType })) {
            val backStackEntry = navController.currentBackStackEntry!!
            val userId = backStackEntry.arguments!!.getInt("userId")
            ShowChatScreen(
                x = Messenger.startCommunication(
                    AuthModel.getCurrentUser().id,
                    userId
                ), viewer = AuthModel.getCurrentUser().id, goBackFun = {
                    navController.navigate("chat")
                })
        }
    }
}
