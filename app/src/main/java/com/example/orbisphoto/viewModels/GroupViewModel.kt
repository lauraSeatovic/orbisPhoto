package com.example.orbisphoto.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.data.GroupCard
import com.example.orbisphoto.data.Post
import com.example.orbisphoto.data.User
import com.example.orbisphoto.repository.RepositoryImpl
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GroupViewModel(private val repository: RepositoryImpl) : ViewModel() {
    private var _viewState = MutableStateFlow<Group?>(null)
    val viewState: StateFlow<Group?> = _viewState.asStateFlow()

    fun showGroup(groupId: String) {
        viewModelScope.launch {
            repository.showGroup(groupId).collect {
                _viewState.value = it
            }
        }
    }
}
