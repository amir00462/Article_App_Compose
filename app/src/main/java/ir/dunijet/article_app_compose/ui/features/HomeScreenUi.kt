package ir.dunijet.article_app_compose.ui.features

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ir.dunijet.article_app_compose.ui.theme.cBackground
import ir.dunijet.article_app_compose.ui.wiegets.HomeContent
import ir.dunijet.article_app_compose.ui.wiegets.HomeDrawer
import ir.dunijet.article_app_compose.ui.wiegets.HomeToolbar
import kotlinx.coroutines.launch

@Composable
fun HomeScreenUi() {

    val activity = (LocalContext.current as? Activity)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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

        HomeContent()

    }

}