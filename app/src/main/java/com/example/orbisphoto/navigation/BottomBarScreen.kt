package com.example.orbisphoto.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector

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
        icon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "profile",
        icon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle
    )
}


