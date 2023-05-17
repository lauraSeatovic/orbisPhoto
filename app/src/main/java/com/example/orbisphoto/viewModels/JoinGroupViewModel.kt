package com.example.orbisphoto.viewModels

import android.provider.Telephony
import androidx.lifecycle.ViewModel
import com.example.orbisphoto.repository.RepositoryImpl

class JoinGroupViewModel(private val repository: RepositoryImpl) : ViewModel() {

    fun joinGroup(key: String, password: String) {
        repository.joinGroup(key, password)
    }
}