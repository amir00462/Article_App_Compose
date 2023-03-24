package ir.dunijet.article_app_compose.ui.features

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.ui.features.homeScreen.HomeViewModel
import ir.dunijet.article_app_compose.ui.theme.cBackground
import ir.dunijet.article_app_compose.ui.widgets.BlogToolbar
import ir.dunijet.article_app_compose.ui.widgets.HomeContent
import ir.dunijet.article_app_compose.ui.widgets.HomeDrawer
import ir.dunijet.article_app_compose.util.Cache
import ir.dunijet.article_app_compose.util.KEY_CACHE
import ir.dunijet.article_app_compose.util.mockArticle

@Composable
fun BlogScreenUi() {

    val navigation = getNavController()
    val article = remember { mutableStateOf( Cache.get(KEY_CACHE) ) }

    Scaffold(
        topBar = {
            //BlogToolbar(onBackClicked = { navigation.popBackStack() }, onInfoClicked = {})
        },
        modifier = Modifier.fillMaxSize()
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            //HomeContent(lazyPagingItems)
        }

    }


}