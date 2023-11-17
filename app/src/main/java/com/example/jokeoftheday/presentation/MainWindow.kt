package com.example.jokeoftheday.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jokeoftheday.presentation.viewmodel.JokeList
import com.example.jokeoftheday.presentation.viewmodel.JokeListViewModel
import com.example.jokeoftheday.presentation.viewmodel.MainViewModel
import com.example.jokeoftheday.ui.theme.Purple700
import com.example.jokeoftheday.util.Routes
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainWindow(){

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = Closed)
    val mainViewModel: MainViewModel = hiltViewModel()
    val jokeListViewModel: JokeListViewModel = hiltViewModel()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(navController = navController, drawerState = drawerState)
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                androidx.compose.material.TopAppBar(
                    modifier = Modifier,
                    elevation = 0.dp,
                    backgroundColor = MaterialTheme.colorScheme.primary
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isOpen) drawerState.close()
                                    else drawerState.open()
                                }
                            }
                        ) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Outlined.Menu,
                                contentDescription = "notification icon",
                                tint = Color.Black
                            )
                        }
                        androidx.compose.material.Text(
                            text = "Get your daily humour",
                            fontSize = 25.sp,
                            style = androidx.compose.material.MaterialTheme.typography.h5,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        ) {
            // it -> paddingvalues which return > 0.0 when bottombar is declared
            NavHost(
                navController = navController,
                startDestination = Routes.Main.route
            ) {
                composable(Routes.Main.route) {
                    JokeMain(
                        topPadding = 50.dp,
                        mainViewModel = mainViewModel
                    )
                }
                composable(Routes.Library.route){
                    JokeList(
                        topPadding = 60.dp,
                        jokeListViewModel = jokeListViewModel
                    )
                }
                composable(Routes.About.route) {
                    AboutPage(60.dp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState
){
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight()
            .padding(20.dp)
            .background(Color.DarkGray)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Yellow)
                .padding(vertical = 100.dp)
                .padding(start = 20.dp)
        ) {
            Text(
                text = "Get your daily humour\nLaughter is the best medicine",
                color = Color.Red,
                fontSize = 30.sp
            ) // drawer Header
        }
        Divider(color = Color.LightGray)
        Spacer(modifier = Modifier.height(20.dp))
        NavigationDrawerItems(navController, drawerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerItems(
    navController: NavHostController,
    drawerState: DrawerState
){
    var scope = rememberCoroutineScope()
    var currentBackStackEntryAsState = navController.currentBackStackEntry
    var destination: NavDestination? = currentBackStackEntryAsState?.destination

     NavigationDrawerItem(
         icon = { Icon(imageVector = Routes.Main.icon, contentDescription = Routes.Main.name) },
         label = { Text(text = Routes.Main.name)},
         selected = destination?.route == Routes.Main.route,
         onClick = {
             navController.navigate(Routes.Main.route){
                 popUpTo(Routes.Main.route)
             }
             scope.launch {
                 drawerState.close()
             }
         },
     )
    Spacer(modifier = Modifier.height(30.dp))
    NavigationDrawerItem(
        icon = { Icon(imageVector = Routes.Library.icon, contentDescription = Routes.Library.name) },
        label = { Text(Routes.Library.name)},
        selected = destination?.route == Routes.Library.route,
        onClick = {
            navController.navigate(Routes.Library.route){
                popUpTo(Routes.Library.route)
            }
            scope.launch {
                drawerState.close()
            }
        }
    )
    Spacer(modifier = Modifier.height(30.dp))
    NavigationDrawerItem(
        icon = { Icon(imageVector = Routes.About.icon, contentDescription = Routes.About.name) },
        label = { Text(Routes.About.name)},
        selected = destination?.route == Routes.About.route,
        onClick = {
            navController.navigate(Routes.About.route){
                popUpTo(Routes.About.route)
            }
            scope.launch {
                drawerState.close()
            }
        }
    )
}