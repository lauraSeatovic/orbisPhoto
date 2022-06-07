package com.example.orbisphoto.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.orbisphoto.data.User
import com.example.orbisphoto.repository.RepositoryImpl
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

class ProfileViewModel(private val repository: RepositoryImpl) : ViewModel(){

    fun signUp(email: String, password: String, username: String ){
        repository.signUp(email, password, username)
    }

    fun signOut(){
        repository.signOut();
    }

    fun logIn(email: String, password: String){
        repository.logIn(email, password)
    }

}