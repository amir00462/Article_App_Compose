package ir.dunijet.article_app_compose.ui.wiegets

import ir.dunijet.article_app_compose.R
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.dunijet.article_app_compose.ui.theme.cBackground

@Composable
fun HomeToolbar(onSearchClicked: () -> Unit , onDrawerClicked: () -> Unit) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(cBackground)
    ) {

        val (search, drawer, imgMain) = createRefs()

        Image(
            modifier = Modifier.constrainAs(imgMain) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            painter = painterResource(id = R.drawable.ic_dunijet),
            contentDescription = "dunijet pic"
        )

        MainButton(modifier = Modifier.constrainAs(search) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end , 16.dp)
        } , R.drawable.ic_search) {
            onSearchClicked.invoke()
        }

        MainButton(modifier = Modifier.constrainAs(drawer) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start , 16.dp)
        } , R.drawable.ic_menu) {
            onDrawerClicked.invoke()
        }

    }

}

// - - - - - - - - - - - - - - - - - - - - -

@Composable
fun HomeDrawer(onCloseDrawer: () -> Unit) {

    BackHandler(onBack = onCloseDrawer)
    Box(modifier = Modifier) {



    }


}

//@Composable
//private fun DrawerMenuItem(
//    iconDrawableId: Int,
//    text: String) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {onItemClick()}
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically,
//    ){
//        Icon(
//            painter = painterResource(iconDrawableId),
//            contentDescription = null,
//        )
//        Spacer(modifier = Modifier.width(16.dp))
//        Text(text = text, )
//    }
//}
//
//@Composable
//fun DrawerBody() {
//    Column {
//
//        DrawerMenuItem(
//            iconDrawableId = R.drawable.ic_code,
//            text = "اطلاعات توسعه دهندگان"
//        )
//
//        DrawerMenuItem(
//            iconDrawableId = R.drawable.ic_info,
//            text = "درباره برنامه",
//        )
//    }
//}
//
//
//@Composable
//fun DrawerHeader(){
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 64.dp)
//        ,
//        horizontalArrangement = Arrangement.Center,
//    ) {
//        Text(text = "Header", fontSize = 60.sp)
//    }
//}

// - - - - - - - - - - - - - - - - - - - - -