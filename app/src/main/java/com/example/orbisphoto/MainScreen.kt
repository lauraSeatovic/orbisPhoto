package com.example.orbisphoto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import com.example.orbisphoto.navigation.BottomBarScreen
import com.example.orbisphoto.navigation.NavGraph
import com.example.orbisphoto.ui.theme.mainColorIndigo
import com.example.orbisphoto.ui.theme.mainColorIndigoLight
import com.example.tmdb.navigation.RootNavGraph
import com.google.firebase.auth.FirebaseAuth


@Composable
fun MainScreen(rootNavController: NavHostController, auth: FirebaseAuth) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavGraph(rootNavController, navController, auth)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(BottomBarScreen.Groups, BottomBarScreen.Recent, BottomBarScreen.Profile)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation() {
        screens.forEach() { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    BottomNavigationItem(
        modifier = Modifier
            .background(Color.White)
            .height(60.dp),
        label = {
            Text(
                text = screen.title,
                color = mainColorIndigo
            )
        },
        selected = selected,
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = null,
                tint = if (selected) mainColorIndigo else mainColorIndigoLight
            )
        },
        onClick = {
            navController.navigate(screen.route)
        },
    )
}
