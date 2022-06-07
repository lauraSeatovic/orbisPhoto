package com.example.orbisphoto.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orbisphoto.data.Group
import com.example.orbisphoto.data.GroupCard
import com.example.orbisphoto.data.Post
import com.example.orbisphoto.repository.GroupResponse
import com.example.orbisphoto.repository.RepositoryImpl
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyGroupsViewModel(private val repository: RepositoryImpl) : ViewModel() {

    private var _viewState = MutableStateFlow<List<GroupCard>>(emptyList())
    val viewState: StateFlow<List<GroupCard>> = _viewState.asStateFlow()


    fun getGroups() {
        viewModelScope.launch {
            repository.getGroups().collect {
                _viewState.value = it
            }
        }
    }

}