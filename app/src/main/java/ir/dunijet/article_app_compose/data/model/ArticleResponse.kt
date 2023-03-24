package ir.dunijet.article_app_compose.data.model

data class ArticleResponse(
    val articleList: List<Article>,
    val meta: Meta
)