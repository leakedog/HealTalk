package com.example.firstprojecttry

import android.graphics.drawable.Icon
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firstprojecttry.ui.theme.FirstProjectTryTheme

@Composable
fun bottomBar() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
    ) {
        Row() {
            Box(modifier = Modifier.width(133.dp)){
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Messages Icon",
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
            Box(modifier = Modifier.width(133.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.messages),
                    contentDescription = "Messages Icon",
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
            Box(modifier = Modifier.width(133.dp)) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Icon",
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    FirstProjectTryTheme {
        bottomBar();
    }
}

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen();
        }

        composable("messages") {
            MessengerScreen()
        }

        composable("profile") {
            ProfileScreen()
        }
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector? = null,
    val painter: Painter? = null,
    val messagesCount: MutableState<Int> = mutableStateOf(Integer.valueOf(0))
)

@OptIn( ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState();
    NavigationBar(
        modifier = modifier,

    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route;
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item)},
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                ),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (item.messagesCount.value > 0) {
                            BadgedBox(
                                badge = { Badge { Text(text = (item.messagesCount.value).toString())} },
                                modifier = Modifier
                                    .width(50.dp)


                            ){
                                if(item.icon == null) {
                                    Icon(
                                        painter = item.painter!!,
                                        contentDescription = item.name,
                                        modifier = Modifier.size(40.dp),
                                    )
                                } else {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.name,
                                        modifier = Modifier.size(40.dp)

                                    )
                                }
                            }
                        } else {
                            if(item.icon == null) {
                                Icon(
                                    painter = item.painter!!,
                                    contentDescription = item.name,
                                    modifier = Modifier.size(40.dp),
                                )
                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        if (selected) {
                           /* Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )

                            */
                        }
                    }
                }
            )
        }
    }

}
@Composable
fun SearchScreen() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Search screen")
    }
}

@Composable
fun MessengerScreen() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Messenger screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile screen")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CheckView() {
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