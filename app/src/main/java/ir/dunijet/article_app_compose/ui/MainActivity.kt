package ir.dunijet.article_app_compose.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.get
import dev.burnoo.cokoin.navigation.KoinNavHost
import ir.dunijet.article_app_compose.di.myModules
import ir.dunijet.article_app_compose.ui.features.BlogScreenUi
import ir.dunijet.article_app_compose.ui.features.HomeScreenUi
import ir.dunijet.article_app_compose.ui.features.SearchScreenUi
import ir.dunijet.article_app_compose.ui.theme.Article_App_ComposeTheme
import ir.dunijet.article_app_compose.ui.theme.cBackground
import ir.dunijet.article_app_compose.util.MyScreens
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {


            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)
            }) {

                Article_App_ComposeTheme {

                    val uiController = rememberSystemUiController()
                    SideEffect { uiController.setStatusBarColor(cBackground) }

                    Surface(color = cBackground, modifier = Modifier.fillMaxSize()) {

                        ArticleApp()

                    }

                }

            }

        }
    }
}

@Composable
fun ArticleApp() {

    val navController = rememberNavController()
    KoinNavHost(navController = navController, startDestination = MyScreens.HomeScreen.route) {

        composable(MyScreens.HomeScreen.route) {
            HomeScreenUi()
        }

        composable(MyScreens.BlogScreen.route) {
            BlogScreenUi()
        }

        composable(MyScreens.SearchScreen.route) {
            SearchScreenUi()
        }

    }

}