package com.example.orbisphoto.ui.mygroups

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.data.GroupCard
import com.example.orbisphoto.ui.theme.mainColorBlue
import com.example.orbisphoto.ui.theme.mainColorIndigo
import com.example.orbisphoto.ui.theme.mainColorPurple
import com.example.orbisphoto.viewModels.MyGroupsViewModel
import com.example.orbisphoto.viewModels.NewGroupViewModel
import org.koin.androidx.compose.viewModel


@Composable
fun MyGroupsScreen(navController: NavController) {
    val myGroupsViewModel: MyGroupsViewModel by viewModel()

    myGroupsViewModel.getGroups()

    val groups = myGroupsViewModel.viewState.collectAsState()
    Log.i("groups", groups.value.toString())

    MyGroupsLayout(
        groups = groups.value,
        onCardClick = { id -> navController.navigate("group_screen/$id") },
        onNewGroupClick = { navController.navigate("new_group") },
        onJoinGroupClick = { navController.navigate("join_group") }
    )
}

@Composable
fun MyGroupsLayout(
    groups: List<GroupCard>,
    onCardClick: (String) -> Unit,
    onNewGroupClick: () -> Unit,
    onJoinGroupClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Title(text = "My Groups", color = mainColorIndigo)
        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(groups.size) { item ->
                GroupInfoCard(groups[item], onCardClick)
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        ButtonNewGroupScreen(text = "Create a New Group", color = mainColorIndigo, onNewGroupClick)
        ButtonNewGroupScreen(text = "Join a New Group", color = mainColorIndigo, onJoinGroupClick)
    }
}

@Composable
fun Title(text: String, color: Color) {

    Text(
        text = text,
        color = mainColorIndigo,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(10.dp),

        )

}

@Composable
fun ButtonNewGroupScreen(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = Color.White),
        modifier = Modifier
            .fillMaxWidth(0.7f)
    ) {
        Text(text = text)
    }

}