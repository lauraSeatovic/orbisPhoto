package com.example.orbisphoto.ui.grouppage

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.orbisphoto.R
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.ui.newpost.HeaderWave
import com.example.orbisphoto.ui.theme.*
import com.example.orbisphoto.viewModels.GroupViewModel
import com.example.orbisphoto.viewModels.NewGroupViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun GroupScreen(navController: NavController, id: String?) {
    val groupViewModel: GroupViewModel by viewModel()
    groupViewModel.showGroup(id!!)
    val group = groupViewModel.viewState.collectAsState()
    if (group.value != null) {
        GroupPageLayout(
            group = group.value!!,
            onNewPostClick = { navController.navigate("new_post/$id") },
            onPhotoCardClick = {}
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupPageLayout(
    group: Group,
    onNewPostClick: () -> Unit,
    onPhotoCardClick: (String) -> Unit
) {
    val options = listOf(
        Pair(Pink, DarkPink),
        Pair(Blue, DarkBlue),
        Pair(Purple, DarkPurple),
        Pair(Peach, DarkPeach)
    )

    val lightColor = options[group.color.toInt()].first
    val darkColor = options[group.color.toInt()].second

    Box(modifier = Modifier.background(Color.White)) {
        Log.i("mygroup", group.photos.toString())
        HeaderWave(group = group)
        Column() {
            Spacer(modifier = Modifier.height(200.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                HeaderButton(text = "Info", darkColor, onNewPostClick)
                HeaderButton(text = "New Post", darkColor, onNewPostClick)
            }
            Spacer(modifier = Modifier.height(19.dp))
            PhotoGrid(group.photos!!, onPhotoCardClick)
        }
    }
}

