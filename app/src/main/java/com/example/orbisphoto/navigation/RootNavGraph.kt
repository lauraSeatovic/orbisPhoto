package com.example.tmdb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.orbisphoto.MainScreen
import com.example.orbisphoto.navigation.RootScreen
import com.example.orbisphoto.ui.grouppage.GroupScreen
import com.example.orbisphoto.ui.joingroup.joinGroupScreen
import com.example.orbisphoto.ui.newgroup.NewGroupScreen
import com.example.orbisphoto.ui.newpost.NewPostScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RootNavGraph(rootNavHostController: NavHostController, auth: FirebaseAuth) {
    NavHost(rootNavHostController, startDestination = RootScreen.Main.route) {
        composable(RootScreen.Main.route) {
            MainScreen(rootNavHostController, auth)
        }

        composable(
            route = RootScreen.Group.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            GroupScreen(rootNavHostController, id = entry.arguments?.getString("id"))
        }
        
        composable(RootScreen.NewGroup.route){
            NewGroupScreen(navController = rootNavHostController)
        }

        composable(
            route = RootScreen.NewPost.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            NewPostScreen(rootNavHostController, id = entry.arguments?.getString("id"))
        }

        composable(RootScreen.JoinGroup.route){
            joinGroupScreen(navController = rootNavHostController)
        }
    }
}