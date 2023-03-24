package ir.dunijet.article_app_compose.ui.features.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.data.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val articleRepository: ArticleRepository) :ViewModel() {

    private var articles: Flow<PagingData<Article>>? = null
    fun getArticles(): Flow<PagingData<Article>> {
        val newResult: Flow<PagingData<Article>> = articleRepository.getArticles().cachedIn(viewModelScope)
        articles = newResult
        return newResult
    }


}