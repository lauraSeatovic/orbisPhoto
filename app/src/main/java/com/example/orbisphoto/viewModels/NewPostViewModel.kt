package com.example.orbisphoto.viewModels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.data.Post
import com.example.orbisphoto.repository.RepositoryImpl
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.time.LocalDateTime
import java.util.*

class NewPostViewModel(private val repository: RepositoryImpl) : ViewModel() {

    fun uploadImage(fileUri: Uri): String = repository.uploadImageToFirebase(fileUri)

    fun newPost(
        groupId: String,
        photo: String,
        description: String
    ): String = repository.savePost(groupId, photo, description)

}