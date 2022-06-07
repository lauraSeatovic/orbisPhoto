package com.example.orbisphoto.ui.mygroups

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.data.GroupCard
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
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(groups.size) { item ->
                GroupInfoCard(groups[item], onCardClick)
            }

        }
        ButtonNewGroupScreen(text = "Create a new group", color = Color.Blue, onNewGroupClick)
        ButtonNewGroupScreen(text = "Join a new group", color = Color.Blue, onJoinGroupClick)
    }
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