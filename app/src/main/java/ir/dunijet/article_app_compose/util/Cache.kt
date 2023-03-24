package ir.dunijet.article_app_compose.util

object Cache {
    private val cacheMap = HashMap<String, Any>()
    private const val EXPIRATION_TIME = 5 * 60 * 1000 // 5 minutes

    fun put(key: String, value: Any) {
        cacheMap[key] = value
    }

    fun get(key: String): Any? {
        val value = cacheMap[key]
        if (value != null && System.currentTimeMillis() - (value as CacheObject).time < EXPIRATION_TIME) {
            return value.data
        }
        cacheMap.remove(key)
        return null
    }

    data class CacheObject(val data: Any, val time: Long)
}