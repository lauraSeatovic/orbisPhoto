package com.example.orbisphoto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.orbisphoto.ui.ProfileScreen
import com.example.orbisphoto.ui.mygroups.MyGroupsScreen
import com.example.orbisphoto.ui.recentspost.RecentPostsScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(
    rootNavHostController: NavHostController,
    bottomBarNavHostController: NavHostController,
    auth: FirebaseAuth
) {
    NavHost(
        navController = bottomBarNavHostController,
        startDestination = BottomBarScreen.Groups.route
    ) {
        composable(route = BottomBarScreen.Groups.route) {
            MyGroupsScreen(rootNavHostController)
        }

        composable(route = BottomBarScreen.Recent.route) {
            RecentPostsScreen(rootNavHostController)
        }

        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(rootNavHostController, auth)
        }
    }
}