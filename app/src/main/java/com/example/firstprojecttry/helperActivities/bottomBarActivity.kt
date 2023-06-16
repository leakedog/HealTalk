package com.example.firstprojecttry.helperActivities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.firstprojecttry.Navigator.NavigatorViewModel
import com.example.firstprojecttry.R
import com.example.firstprojecttry.Upload.uploadModel


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
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = NavigatorViewModel.getNavController().currentBackStackEntryAsState();
    NavigationBar(
        modifier = modifier,
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route;
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item)},
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor =  colorResource(id = R.color.purple_500),
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
                            // Show name of bottom bar button or not?
                            /*
                           Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyLarge
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
fun BottomBar() {
    val ve = remember{ mutableIntStateOf(Integer.valueOf(0)) };
    ve.value = 23;
    BottomNavigationBar(
        items = listOf(
            BottomNavItem(
                name = "Search",
                route = "search",
                painter = painterResource(id = R.drawable.search)
            ),
            BottomNavItem(
                name = "Messages",
                route = "chat",
                painter = painterResource(id = R.drawable.messages),
                messagesCount = ve
            ),
            BottomNavItem(
                name = "Profile",
                route = "profile",
                icon = Icons.Default.Person,
            )
        ),
        onItemClick = {
            NavigatorViewModel.showByRoute(it.route);
        }
    )
}
