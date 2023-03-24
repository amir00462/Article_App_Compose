package ir.dunijet.article_app_compose.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.data.net.ApiService
import ir.dunijet.article_app_compose.util.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class ArticleDataSource(private val apiService: ApiService) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = apiService.getArticles(params.loadSize, page)
            val articles = response.articleList
            LoadResult.Page(
                data = articles,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.meta.next_page == null) null else page + 1
            )
        } catch (exception: IOException) {
            val error = IOException("لطفا دسترسی به اینترنت را بررسی کنید")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
}