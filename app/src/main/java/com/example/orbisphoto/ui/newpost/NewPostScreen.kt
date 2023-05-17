package com.example.orbisphoto.ui.newpost

import android.net.Uri
import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.ui.ActionButton
import com.example.orbisphoto.ui.grouppage.*
import com.example.orbisphoto.ui.newgroup.CreateButton
import com.example.orbisphoto.ui.newgroup.PhotoPicker
import com.example.orbisphoto.ui.theme.*
import com.example.orbisphoto.viewModels.NewGroupViewModel
import com.example.orbisphoto.viewModels.NewPostViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun NewPostScreen(navController: NavController, id: String?) {
    var photo by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val newPostViewModel: NewPostViewModel by viewModel()

    newPostViewModel.showGroup(id!!)
    val group = newPostViewModel.viewState.collectAsState()
    if (group.value != null) {

        NewPostLayout(
            group = group.value!!,
            photo,
            description,
            onPostClick = { newPostViewModel.newPost(id!!, photo, description) },
            onDescriptionChange = { newDescription -> description = newDescription },
            selectImage = { imageURI -> photo = newPostViewModel.uploadImage(imageURI) },
        )
    }
}

@Composable
fun NewPostLayout(
    group: Group,
    photo: String,
    description: String,
    onPostClick: () -> Unit,
    onDescriptionChange: (String) -> Unit,
    selectImage: (Uri) -> Unit,

    ) {
    val options = listOf(
        Pair(Pink, DarkPink),
        Pair(Blue, DarkBlue),
        Pair(Purple, DarkPurple),
        Pair(Peach, DarkPeach)
    )

    val lightColor = options[group.color.toInt()].first
    val darkColor = options[group.color.toInt()].second
    HeaderWave(group)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(modifier = Modifier.height(240.dp))
        PhotoPicker(selectImage = selectImage, darkColor)
        DescriptionBox(
            text = description,
            onValueChange = onDescriptionChange,
            lightColor,
            darkColor
        )
        ActionButton(text = "Post", color = darkColor, onPostClick)
    }
}


@Composable
fun HeaderWave(group: Group) {
    val options = listOf(
        Pair(Pink, DarkPink),
        Pair(Blue, DarkBlue),
        Pair(Purple, DarkPurple),
        Pair(Peach, DarkPeach)
    )

    val lightColor = options[group.color.toInt()].first
    ColoredWave(color = lightColor)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GroupImage(image = group.image)
        GroupName(name = group.name)
    }


}

@Composable
fun DescriptionBox(
    text: String,
    onValueChange: (String) -> Unit,
    lightColor: Color,
    darkColor: Color
) {

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        maxLines = 5,
        singleLine = false,
        modifier = Modifier
            .height(100.dp)
            .width(300.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = lightColor,
            focusedIndicatorColor = darkColor,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(text = "Description") }

    )
}