package com.example.orbisphoto.viewModels

import android.net.Uri
import android.util.Log
import com.example.orbisphoto.data.Group
import androidx.lifecycle.ViewModel
import com.example.orbisphoto.data.Post
import com.example.orbisphoto.data.User
import com.example.orbisphoto.repository.RepositoryImpl
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class NewGroupViewModel(private val repository: RepositoryImpl) : ViewModel() {
    fun newGroup(name: String, password: String, image: String, color: Int): String = repository.saveGroup(name, password, image, color)

    fun uploadImage(fileUri: Uri): String = repository.uploadImageToFirebase(fileUri)
}