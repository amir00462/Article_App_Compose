package ir.dunijet.article_app_compose.ui.features.homeScreen

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import dev.burnoo.cokoin.navigation.getNavViewModel
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.ui.theme.cBackground
import ir.dunijet.article_app_compose.ui.widgets.HomeContent
import ir.dunijet.article_app_compose.ui.widgets.HomeDrawer
import ir.dunijet.article_app_compose.ui.widgets.HomeToolbar
import ir.dunijet.article_app_compose.util.listArticle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf

@Composable
fun HomeScreenUi() {

    val viewModel = getNavViewModel<HomeViewModel>()
    val activity = (LocalContext.current as? Activity)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val articles = viewModel.getArticles()
    val lazyPagingItems = flowOf(PagingData.from(listArticle)).collectAsLazyPagingItems()

//    LaunchedEffect(null) {
//        scope.launch {
//            articles.collectLatest {
//
//                it.map {
//                    Log.v("testArticle", it.toString())
//                }
//
//            }
//        }
//    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeToolbar(onDrawerClicked = {
                scope.launch { scaffoldState.drawerState.open() }
            }, onSearchClicked = {

            })
        },
        modifier = Modifier.fillMaxSize(),
        drawerGesturesEnabled = true,
        drawerContent = {
            HomeDrawer() {

                scope.launch {

                    if (scaffoldState.drawerState.currentValue == DrawerValue.Open) {
                        scaffoldState.drawerState.close()
                    } else {
                        activity?.finish()
                    }

                }
            }
        },
        drawerElevation = 2.dp,
        drawerBackgroundColor = cBackground
    ) {

        HomeContent(lazyPagingItems)

    }

}