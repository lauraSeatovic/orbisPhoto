package com.example.orbisphoto.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.orbisphoto.ui.theme.mainColorIndigo
import com.example.orbisphoto.viewModels.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.compose.viewModel


@Composable
fun ProfileScreen(
    navController: NavController,
    auth: FirebaseAuth
) {
    if(auth.currentUser != null) {
        auth.currentUser!!.email?.let { Log.i("user", it) }
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    val profileViewModel: ProfileViewModel by viewModel()
    ProfileLayout(
        email,
        password,
        username,
        onLogInClick = {profileViewModel.logIn(email, password)
        },
        onSignOutClick = {profileViewModel.signOut()},
        onSignUpClick = {profileViewModel.signUp(email, password, username)
        },
        onEmailChange = { newEmail -> email = newEmail },
        onPasswordChange = { newPass -> password = newPass },
        onUsernameChange = {newUsername -> username = newUsername}
    )
}


@Composable
fun ProfileLayout(
    email: String,
    password: String,
    username: String,
    onLogInClick: () -> Unit,
    onSignOutClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        InputWidget(label = "Email", maxChar = 15, email, onEmailChange)
        InputWidget(label = "Password", maxChar = 15, password, onPasswordChange)
        InputWidget(label = "Username", maxChar = 15, username, onUsernameChange)
        ActionButton(text = "Log In", color = mainColorIndigo, onLogInClick)
        ActionButton(text = "Log out", color = mainColorIndigo, onSignOutClick)
        ActionButton(text = "Sign Up", color = mainColorIndigo, onSignUpClick)
    }
}

@Composable
fun InputWidget(label: String, maxChar: Int, text: String, onValueChange: (String) -> Unit) {

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = mainColorIndigo,
            textColor = mainColorIndigo,
            focusedLabelColor = mainColorIndigo,
            cursorColor = mainColorIndigo
        )
    )
}

@Composable
fun ActionButton(
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