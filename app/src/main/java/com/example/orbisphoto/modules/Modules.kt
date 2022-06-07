package com.example.tmdb.modules



import com.example.orbisphoto.repository.RepositoryImpl
import com.example.orbisphoto.viewModels.*
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel



val appModule = module {
    single { RepositoryImpl() }
    viewModel { ProfileViewModel(get()) }
    viewModel { GroupViewModel(get()) }
    viewModel { MyGroupsViewModel(get()) }
    viewModel { NewGroupViewModel(get()) }
    viewModel { NewPostViewModel(get()) }
    viewModel { RecentPostsViewModel(get()) }
    viewModel { JoinGroupViewModel(get()) }


}