package com.example.orbisphoto.data

data class Group(
    val groupId: String?,
    val name: String,
    val password: String,
    val image: String,
    val color: String,
    var photos: List<Post>?
)
