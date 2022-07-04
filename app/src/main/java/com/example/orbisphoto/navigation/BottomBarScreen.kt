package com.example.orbisphoto.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.orbisphoto.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {
    object Groups : BottomBarScreen(
        route = "groups",
        title = "Groups",
        icon = Icons.Outlined.List,
        selectedIcon = Icons.Filled.List
    )

    object Recent : BottomBarScreen(
        route = "recent_posts",
        title = "Recent",
        icon = Icons.Outlined.Image ,
        selectedIcon = Icons.Outlined.Image
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle
    )
}


