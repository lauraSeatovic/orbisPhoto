package com.example.orbisphoto.navigation


sealed class RootScreen(
    val route: String,
    val title: String
) {
    object Main : RootScreen(
        route = "main",
        title = "Main"
    )

    object Group : RootScreen(
        route = "group_screen/{id}",
        title = "Group"
    )

    object NewGroup : RootScreen(
        route = "new_group",
        title = "New Group"
    )

    object NewPost : RootScreen(
        route = "new_post/{id}",
        title = "New Post"
    )

    object JoinGroup : RootScreen(
        route = "join_group",
        title = "Join a New Group"
    )

}
