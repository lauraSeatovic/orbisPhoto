package com.example.orbisphoto.data

import java.util.*

data class Post (
    val postId: String?,
    val groupId: String,
    val description: String,
    val image: String,
    val date: String
)