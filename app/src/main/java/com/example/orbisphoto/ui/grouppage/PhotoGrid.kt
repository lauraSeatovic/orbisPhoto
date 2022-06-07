package com.example.orbisphoto.ui.grouppage

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.data.Post
import com.google.firebase.storage.FirebaseStorage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGrid(map: List<Post>, onPhotoCardClick: (String) -> Unit) {
    var posts = map.toList()
    Spacer(Modifier.height(10.dp))
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.Center),
            content = {

                items(posts.size) { index ->
                    PhotoCard(posts[index], onPhotoCardClick)

                }
            })
    }
}

@Composable
fun PhotoCard(post: Post, onPhotoCardClick: (String) -> Unit) {
    Image(
        painter = rememberAsyncImagePainter(post.image),
        contentDescription = null,
        modifier = Modifier
            .height(170.dp)
            .width(170.dp)
            .padding(3.dp)
            .clickable { onPhotoCardClick(post.postId!!) },
        contentScale = ContentScale.Crop,

    )
}