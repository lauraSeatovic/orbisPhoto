package com.example.orbisphoto.ui.newpost

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.orbisphoto.ui.newgroup.CreateButton
import com.example.orbisphoto.ui.newgroup.PhotoPicker
import com.example.orbisphoto.viewModels.NewGroupViewModel
import com.example.orbisphoto.viewModels.NewPostViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun NewPostScreen(navController: NavController, id: String?) {
    var photo by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val newPostViewModel: NewPostViewModel by viewModel()

    NewPostLayout(
        photo,
        description,
        onPostClick = {newPostViewModel.newPost(id!!, photo, description)},
        onDescriptionChange = { newDescription -> description = newDescription },
        selectImage = { imageURI -> photo = newPostViewModel.uploadImage(imageURI) },
    )
}

@Composable
fun NewPostLayout(
    photo: String,
    description: String,
    onPostClick: () -> Unit,
    onDescriptionChange: (String) -> Unit,
    selectImage: (Uri) -> Unit,

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        PhotoPicker(selectImage)
        CreateButton(text = "Create", color = Color.Blue, onPostClick)
    }
}