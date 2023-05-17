package com.example.orbisphoto.ui.grouppage

import android.graphics.Bitmap
import android.icu.text.IDNA
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orbisphoto.R
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.rememberAsyncImagePainter
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.ui.theme.*
import com.google.firebase.storage.FirebaseStorage


@Composable
fun GroupInfo(group: Group, darkColor: Color, onNewPostClick: () -> Unit) {
    Column() {
        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GroupImage(group.image)
                Column(
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween

                ) {
                    GroupName(name = group.name)
                }
            }
        }
        //Divider(thickness = 1.dp)
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            HeaderButton(text = "Info", darkColor, onNewPostClick)
            HeaderButton(text = "New Post", darkColor, onNewPostClick)
        }
        Spacer(modifier = Modifier.height(19.dp))
    }

}

@Composable
fun GroupImage(image: String) {
    Card(elevation = 10.dp, modifier = Modifier
        .padding(start = 30.dp)
        .height(130.dp)
        .width(130.dp)) {
        Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = "Group photo",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun HeaderButton(text: String, color: Color, action: () -> Unit) {
    Button(
        onClick = { action() },
        colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = Color.White),
        modifier = Modifier.width(150.dp)
    ) {
        Text(text = text)
    }

}

@Composable
fun GroupName(name: String) {
    Text(
        text = name,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White ,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )

}

@Composable
private fun GroupDescription(description: String) {
    Text(
        text = description,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier
            .width(100.dp)
            .padding(8.dp)
    )

}
