package ir.dunijet.article_app_compose.ui.features.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.data.repository.ArticleRepository
import ir.dunijet.article_app_compose.util.NETWORK_PAGE_SIZE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class HomeViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    val articles: Flow<PagingData<Article>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { articleRepository.getData() }
    )
        .flow
        .cachedIn(viewModelScope)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getArticleById(id: Int): Article? {
        var article: Article? = null

        articles.mapLatest { page ->
            page.map {
                if (it.id == id) {
                    article = it
                }
            }
        }

        return article
    }

    // val article: Article? by viewModel.getArticleById(id).collectAsState(null)

}