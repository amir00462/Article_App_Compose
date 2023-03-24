package ir.dunijet.article_app_compose.data.dataSource

import android.annotation.SuppressLint
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.data.net.ApiService
import ir.dunijet.article_app_compose.util.STARTING_PAGE_INDEX
import ir.dunijet.article_app_compose.util.mockArticle
import retrofit2.HttpException
import java.io.IOException
import java.lang.Integer.max

class ArticleDataSource(private val apiService: ApiService) : PagingSource<Int, Article>() {

    // fake data function
    @SuppressLint("NewApi")
    private fun ensureValidKey(key: Int) = max(STARTING_PAGE_INDEX, key)

    // fake
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val start = params.key ?: STARTING_PAGE_INDEX
        val range = start.until(start + params.loadSize)

        return LoadResult.Page(

            data = range.map { it ->
                mockArticle.copy(id = it)
            },

            prevKey = when (start) {
                STARTING_PAGE_INDEX -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },

            nextKey = range.last + 1

        )

    }

    // fake
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }

    // network call load
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//        val page = params.key ?: STARTING_PAGE_INDEX
//
//        return try {
//            val response = apiService.getArticles(params.loadSize, page)
//            val articles = response.articleList
//            LoadResult.Page(
//                data = articles,
//                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
//                nextKey = if (response.meta.next_page == null) null else page + 1
//            )
//        } catch (exception: IOException) {
//            val error = IOException("لطفا دسترسی به اینترنت را بررسی کنید")
//            LoadResult.Error(error)
//        } catch (exception: HttpException) {
//            LoadResult.Error(exception)
//        }
//
//    }

    // network call
//    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//        return state.anchorPosition
//    }

}