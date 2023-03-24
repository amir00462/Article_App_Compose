package ir.dunijet.article_app_compose.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.navArgument
import dev.burnoo.cokoin.navigation.getNavController
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.ui.theme.cBackground
import ir.dunijet.article_app_compose.ui.widgets.BlogToolbar
import ir.dunijet.article_app_compose.ui.widgets.HomeContent
import ir.dunijet.article_app_compose.ui.widgets.HomeDrawer

@Composable
fun BlogScreenUi(currentArticle: Article) {

    val args by navArgument()
    val article: Article = args.user
    val navigation = getNavController()

    Scaffold(
        topBar = {
            BlogToolbar(onBackClicked = { navigation.popBackStack() }, onInfoClicked = {})
        },
        modifier = Modifier.fillMaxSize()
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            HomeContent(lazyPagingItems)
        }

    }


}