package ir.dunijet.article_app_compose.data.model

data class Article(
    val id: Int,

    val title: String,
    val detailText: String,
    val picUrl: String,
    val category: String,
    val tags: List<String>
)