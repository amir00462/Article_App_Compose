package ir.dunijet.article_app_compose.util

import ir.dunijet.article_app_compose.data.model.Article

object Cache {
    private val cacheMap = HashMap<String, CacheObject>()
    private const val EXPIRATION_TIME = 5 * 60 * 1000 // 5 minutes

    fun put(key: String, value: Article) {
        cacheMap[key] = CacheObject(value, System.currentTimeMillis())
    }

    fun get(key: String): Article {
        val value = cacheMap[key]

        if (value != null && System.currentTimeMillis() - value.time < EXPIRATION_TIME) {
            return value.data
        }

        cacheMap.remove(key)
        return mockArticle
    }

    data class CacheObject(val data: Article, val time: Long)
}