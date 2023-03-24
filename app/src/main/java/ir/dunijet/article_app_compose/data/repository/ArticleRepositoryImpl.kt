package ir.dunijet.article_app_compose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.data.dataSource.ArticleDataSource
import ir.dunijet.article_app_compose.data.net.ApiService
import ir.dunijet.article_app_compose.util.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class ArticleRepositoryImpl(private val apiService: ApiService) : ArticleRepository {

    override fun getArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { ArticleDataSource(apiService) }
        ).flow
    }

}