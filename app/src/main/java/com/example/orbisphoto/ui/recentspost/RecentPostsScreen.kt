package com.example.orbisphoto.ui.recentspost

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.orbisphoto.data.Post
import com.example.orbisphoto.ui.mygroups.GroupInfoCard
import com.example.orbisphoto.viewModels.MyGroupsViewModel
import com.example.orbisphoto.viewModels.ProfileViewModel
import com.example.orbisphoto.viewModels.RecentPostsViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun RecentPostsScreen(
    navController: NavController,
) {
    val recentPostsViewModel: RecentPostsViewModel by viewModel()

    recentPostsViewModel.getPosts()

    val posts = recentPostsViewModel.viewState.collectAsState()
    Log.i("posts", posts.value.toString())
    RecentPostsLayout(posts = posts.value)

}


@Composable
fun RecentPostsLayout(
    posts: List<Post>
) {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(posts.size) { item ->
                FeedPostCard(posts[item])
            }

        }
    }
}

@Composable
fun FeedPostCard(post: Post) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
    ) {
        Log.i("image", post.image)
        Image(
            painter = rememberAsyncImagePainter(post.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

}