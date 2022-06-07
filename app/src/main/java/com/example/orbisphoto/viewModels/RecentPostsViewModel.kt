package com.example.orbisphoto.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orbisphoto.data.GroupCard
import com.example.orbisphoto.data.Post
import com.example.orbisphoto.repository.RepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecentPostsViewModel(private val repository: RepositoryImpl) : ViewModel() {
    private var _viewState = MutableStateFlow<List<Post>>(emptyList())
    val viewState: StateFlow<List<Post>> = _viewState.asStateFlow()


    fun getPosts() {
        viewModelScope.launch {
            repository.getRecentPosts().collect {
                _viewState.value = it
            }
        }
    }

}