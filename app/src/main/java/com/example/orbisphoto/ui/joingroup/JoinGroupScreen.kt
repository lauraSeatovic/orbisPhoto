package com.example.orbisphoto.ui.joingroup

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
import com.example.orbisphoto.ui.ActionButton
import com.example.orbisphoto.ui.InputWidget
import com.example.orbisphoto.ui.mygroups.Title
import com.example.orbisphoto.ui.theme.mainColorIndigo
import com.example.orbisphoto.viewModels.JoinGroupViewModel
import com.example.orbisphoto.viewModels.NewGroupViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun joinGroupScreen(
    navController: NavController,
) {
    var key by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val joinGroupViewModel: JoinGroupViewModel by viewModel()

    joinGroupLayout(
        key,
        password,
        onKeyChange = { newKey -> key = newKey },
        onPasswordChange = { newPass -> password = newPass },
        onJoinClick = { joinGroupViewModel.joinGroup(key, password) }
    )
}

@Composable
fun joinGroupLayout(
    key: String,
    password: String,
    onKeyChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onJoinClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Title(text = "Join a New Group", mainColorIndigo)
        InputWidget(label = "Name", maxChar = 15, key, onKeyChange)
        InputWidget(label = "Password", maxChar = 15, password, onPasswordChange)
        ActionButton(text = "Join", color = mainColorIndigo, onJoinClick)
    }

}