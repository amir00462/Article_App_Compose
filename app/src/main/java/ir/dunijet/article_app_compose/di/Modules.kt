package ir.dunijet.article_app_compose.di

import ir.dunijet.article_app_compose.data.net.createApiService
import ir.dunijet.article_app_compose.data.repository.ArticleRepository
import ir.dunijet.article_app_compose.data.repository.ArticleRepositoryImpl
import ir.dunijet.article_app_compose.ui.features.homeScreen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    single { createApiService() }
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
    viewModel { HomeViewModel(get()) }

}