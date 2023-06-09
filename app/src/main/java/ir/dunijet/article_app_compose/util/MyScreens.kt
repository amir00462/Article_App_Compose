package ir.dunijet.article_app_compose.util

sealed class MyScreens(val route: String) {
    object HomeScreen : MyScreens("homeScreen")
    object BlogScreen : MyScreens("blogScreen")
    object SearchScreen : MyScreens("searchScreen")
}
