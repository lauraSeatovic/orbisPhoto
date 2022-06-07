package com.example.orbisphoto.ui.mygroups

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.data.GroupCard
import com.example.orbisphoto.ui.theme.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URI


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupInfoCard(group: GroupCard, onCardClick: (String) -> Unit) {
    val options = listOf(
        Pair(Pink, DarkPink),
        Pair(Blue, DarkBlue),
        Pair(Purple, DarkPurple),
        Pair(Peach, Peach))

    val lightColor = options[group.color.toInt()].first
    val darkColor = options[group.color.toInt()].second

    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(vertical = 10.dp),
        onClick = { onCardClick(group.groupId!!) }
    ) {
        Row(modifier = Modifier.background(lightColor)) {
            Image(
                painter = rememberAsyncImagePainter(Uri.parse(group.image)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(130.dp)
                    .padding(end = 5.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = group.name, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
            }
        }

    }
}