package ir.dunijet.article_app_compose.data.repository

import androidx.paging.PagingData
import ir.dunijet.article_app_compose.data.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    fun getArticles(): Flow<PagingData<Article>>

}