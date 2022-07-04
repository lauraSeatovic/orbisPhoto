package com.example.orbisphoto.viewModels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.data.Post
import com.example.orbisphoto.repository.RepositoryImpl
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*

class NewPostViewModel(private val repository: RepositoryImpl) : ViewModel() {

    private var _viewState = MutableStateFlow<Group?>(null)
    val viewState: StateFlow<Group?> = _viewState.asStateFlow()

    fun showGroup(groupId: String) {
        viewModelScope.launch {
            repository.showGroup(groupId).collect {
                _viewState.value = it
            }
        }
    }

    fun uploadImage(fileUri: Uri): String = repository.uploadImageToFirebase(fileUri)

    fun newPost(
        groupId: String,
        photo: String,
        description: String
    ): String = repository.savePost(groupId, photo, description)

}