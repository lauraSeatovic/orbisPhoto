package com.example.orbisphoto.ui.newgroup

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.orbisphoto.ui.InputWidget
import com.example.orbisphoto.ui.theme.*
import com.example.orbisphoto.viewModels.NewGroupViewModel
import com.example.orbisphoto.viewModels.ProfileViewModel
import kotlinx.coroutines.test.withTestContext
import org.koin.androidx.compose.viewModel


@Composable
fun NewGroupScreen(
    navController: NavController,
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var groupKey by remember { mutableStateOf("") }
    var fileName by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(0) }

    val newGroupViewModel: NewGroupViewModel by viewModel()
    NewGroupLayout(
        name,
        password,
        onCreateClick = { groupKey = newGroupViewModel.newGroup(name, password, fileName, color) },
        onNameChange = { newName -> name = newName },
        onPasswordChange = { newPass -> password = newPass },
        groupKey = groupKey,
        selectImage = { imageURI -> fileName = newGroupViewModel.uploadImage(imageURI) },
        selectedIndex = color,
        onColorClick = {newColor -> color = newColor}

    )
}

@Composable
fun NewGroupLayout(
    name: String,
    password: String,
    onCreateClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    groupKey: String,
    selectImage: (Uri) -> Unit,
    selectedIndex: Int,
    onColorClick: (Int) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        Title(title = "Create a new group")
        InputWidget(label = "Name", maxChar = 15, name, onNameChange)
        InputWidget(label = "Password", maxChar = 15, password, onPasswordChange)
        Row(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 5.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Theme:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            ColorGroup(selectedIndex, onColorClick)
        }
        PhotoPicker(selectImage)
        CreateButton(text = "Create", color = Color.Blue, onCreateClick)
        Row(
            modifier = Modifier.fillMaxWidth(0.6f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Key:", fontWeight = FontWeight.Bold)
            Text(groupKey)
        }
    }
}

@Composable
fun Title(title: String) {
    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
}

@Composable
fun CreateButton(
    text: String,
    color: Color,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = { onButtonClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = Color.White),
        modifier = Modifier.width(150.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun PhotoPicker(selectImage: (Uri) -> Unit) {
    //var imageData = remember { mutableStateOf<Uri?>(null) }
    //val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            selectImage(it)
        }
    Button(
        onClick = { launcher.launch("image/*") },
        content = {
            Text(text = "Select Image From Gallery")
        })
    /*
    imageData.let {
        val bitmap = remember { mutableStateOf<Bitmap?>(null) }
        val uri = it.value
        if (uri != null) {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, uri)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, uri)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value.let { btm ->
                if (btm != null) {
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
        }

    }

     */
}

@Composable
fun ColorGroup(selectedIndex: Int, onColorClick: (Int) -> Unit) {
    val options = listOf(
        Pair(Pink, DarkPink),
        Pair(Blue, DarkBlue),
        Pair(Purple, DarkPurple),
        Pair(Peach, Peach)
    )
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        modifier = Modifier.padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(options.size) { item ->
            Box(
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .padding(horizontal = 2.dp)
                    .size(20.dp)
                    .border(
                        1.dp,
                        color = if (options.indexOf(options[item]) == selectedIndex) Color.Black else options[item].first
                    )
                    .background(options[item].first)
                    .selectable(
                        selected = options.indexOf(options[item]) == selectedIndex,
                        onClick = {onColorClick(options.indexOf(options[item]))
                        })
            )
        }
    }
}