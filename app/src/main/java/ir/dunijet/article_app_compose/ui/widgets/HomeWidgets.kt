package ir.dunijet.article_app_compose.ui.widgets

import android.widget.Toast
import ir.dunijet.article_app_compose.R
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.compose.LazyPagingItems
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.ui.theme.*
import ir.dunijet.article_app_compose.util.NetworkChecker
import kotlinx.coroutines.launch

@Composable
fun HomeToolbar(onSearchClicked: () -> Unit, onDrawerClicked: () -> Unit) {

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
            end.linkTo(parent.end, 16.dp)
        }, R.drawable.ic_search) {
            onSearchClicked.invoke()
        }

        MainButton(modifier = Modifier.constrainAs(drawer) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start, 16.dp)
        }, R.drawable.ic_menu) {
            onDrawerClicked.invoke()
        }

    }

}

// - - - - - - - - - - - - - - - - - - - - -

@Composable
fun HomeDrawer(onCloseDrawer: () -> Unit) {

    BackHandler(onBack = onCloseDrawer)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (exit, body) = createRefs()

        MainButton(modifier = Modifier.constrainAs(exit) {

            top.linkTo(parent.top, 14.dp)
            end.linkTo(parent.end, 16.dp)

        }, src = R.drawable.ic_close) {
            onCloseDrawer.invoke()
        }

        DrawerBody(modifier = Modifier.constrainAs(body) {
            start.linkTo(parent.start)
            top.linkTo(exit.bottom, 29.dp)
        })

    }

}

@Composable
private fun DrawerMenuItem(
    iconDrawableId: Int,
    text: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    var rotation by remember { mutableStateOf(0f) }
    val showDetail by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val rotationAnimation = remember { Animatable(0f) }

    Column {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
//                interactionSource = interactionSource,
//                indication = null
                ) {

                    scope.launch {

                        if (rotation == 0f) {
                            rotation = -90f

                            rotationAnimation.animateTo(
                                targetValue = -90f,
                                animationSpec = tween(durationMillis = 300)
                            )
                        } else {
                            rotation = 0f

                            rotationAnimation.animateTo(
                                targetValue = 0f,
                                animationSpec = tween(durationMillis = 300)
                            )
                        }

                    }

                    onClick.invoke()
                }
                .padding(top = 14.dp, bottom = 14.dp)
        ) {

            val (title, arrow, detail) = createRefs()

            Row(
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .padding(start = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(iconDrawableId),
                    tint = cPrimary,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.subtitle2
                )

            }

            Icon(
                modifier = Modifier
                    .constrainAs(arrow) {
                        end.linkTo(parent.end, 25.dp)
                        top.linkTo(title.top)
                        bottom.linkTo(title.bottom)
                    }
                    .size(15.dp, 18.dp)
                    .rotate(rotationAnimation.value),
                tint = cArrow,
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null,
            )

        }

        //if (rotation == -90f) {

        AnimatedVisibility(
            visible = rotation == -90f,

            enter = slideInHorizontally(
                initialOffsetX = { 30 },
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(animationSpec = tween(durationMillis = 300)),

            exit = slideOutVertically(
                targetOffsetY = { 30 },
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(animationSpec = tween(durationMillis = 300))

        ) {

            if (text == "اطلاعات توسعه دهندگان") {
                DevelopersIds()
            } else {

                AppInfo(
                    modifier = Modifier.padding(
                        top = 8.dp,
                        start = 18.dp,
                        end = 25.dp,
                        bottom = 16.dp
                    )
                )

            }

        }

        //}

    }

}

@Composable
fun DrawerBody(modifier: Modifier) {
    Column(modifier = modifier) {

        DrawerMenuItem(
            iconDrawableId = R.drawable.ic_code,
            text = "اطلاعات توسعه دهندگان"
        ) {


        }

        DrawerMenuItem(
            iconDrawableId = R.drawable.ic_info,
            text = "درباره برنامه",
        ) {


        }
    }
}

@Composable
fun AppInfo(modifier: Modifier) {

    val appInfo = """
        سلام رفقا، صفر تا صد پیاده سازی این این اپ به همراه پیاده سازی بک اند و داشبورد فرانت اند در دوره تیم گیت سایت دانیجت پیاده شده.
        در این دوره شما استفاده از گیت و گیت هاب برای مدیریت پروژه در یک تیم برنامه نویسی ۴ نفره به همراه مدیر پروژه و طراح ui رو یاد میگیرید که در آموزش های فارسی گیت بی نظیر و بسیار کارامده.
        این پروژه open source هست و کد اپ و پنل سایت و بک اند رو میتونین در گیت هاب آیدی dunijet به اسم team git پیدا کنین.
        برای تهیه این دوره کاربری، دوره تیم گیت رو در اینترنت سرچ کنین یا به سایت دانیجت سر بزنین :)
    """.trimIndent()

    Column(modifier = modifier) {

        Text(
            text = appInfo,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.h4
        )

        Text(
            modifier = Modifier
                .padding(start = 18.dp, top = 10.dp)
                .clickable { },
            text = "صفحه گیت هاب پروژه",
            color = cPrimary,
            style = MaterialTheme.typography.h4
        )

        Text(
            modifier = Modifier
                .padding(start = 18.dp, top = 10.dp)
                .clickable { },
            text = "ورود به دوره تیم گیت",
            color = cPrimary,
            style = MaterialTheme.typography.h4
        )

        Text(
            modifier = Modifier
                .padding(start = 18.dp, top = 10.dp)
                .clickable { },
            text = "تیم گیت تو کافه بازار",
            color = cPrimary,
            style = MaterialTheme.typography.h4
        )

    }


}

@Composable
fun DevelopersIds() {

    Column {
        Developer(R.drawable.i1, "محمد احمدی", "برنامه نویس اپ اندروید", "@amir00462")
        Developer(R.drawable.i2, "زهرا قاسمی", "برنامه نویس پنل فرانت", "@zahraghasemi")
        Developer(R.drawable.i3, "احمد مهدوی", "برنامه نویس بک اند", "@ahmad_mhd")
        Developer(R.drawable.i4, "سامان کریم\u200Cپور", "مدیر پروژه", "@saman.karim.p")
    }

}

@Composable
private fun Developer(
    iconDrawableId: Int,
    title: String,
    detail: String,
    page: String
) {
    val interactionSource = remember { MutableInteractionSource() }
    val uriHandler = LocalUriHandler.current

//    val rotation by remember { mutableStateOf(0f) }
//    val scope = rememberCoroutineScope()
//    val rotationAnimation = remember { Animatable(0f) }

    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 18.dp)
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            uriHandler.openUri("https://www.instagram.com/" + page.substring(1))
        }) {

        val (nameDeveloper, imgInsta, details) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(nameDeveloper) {
                    top.linkTo(imgInsta.top)
                    start.linkTo(imgInsta.end)
                }
                .padding(start = 18.dp),
            text = title,
            color = cText1,
            style = MaterialTheme.typography.h4
        )

        CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Ltr) {

            Text(
                modifier = Modifier
                    .constrainAs(details) {
                        top.linkTo(nameDeveloper.bottom)
                        start.linkTo(imgInsta.end)
                    }
                    .padding(end = 18.dp, top = 2.dp),
                text = detail + " - " + page,
                color = cText3,
                style = MaterialTheme.typography.overline
            )


        }

        Box(modifier = Modifier
            .constrainAs(imgInsta) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
            .padding(start = 16.dp), contentAlignment = Alignment.Center)
        {

            Image(
                modifier = Modifier.size(52.dp),
                painter = painterResource(id = R.drawable.ic_ring),
                contentDescription = null
            )

            Image(
                modifier = Modifier
                    .size(48.dp),
                painter = painterResource(id = iconDrawableId),
                contentDescription = null
            )

        }

    }

}

// - - - - - - - - - - - - - - - - - - - - -

@Composable
fun HomeContent(lazyPagingData :LazyPagingItems<Article>?) {
    val context = LocalContext.current
    var internetConnected by remember { mutableStateOf(true) }
    val jobState = remember { mutableStateOf(2) } // 1:loading , 2:ok , 3:noArticle
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    // Check internet
    val isConnected = NetworkChecker(context).isInternetConnected
    Toast.makeText(context, isConnected.toString(), Toast.LENGTH_SHORT).show()
    if (!isConnected) {
        internetConnected = false
        jobState.value = 3
    }
//    else {
//        internetConnected = true
//        jobState.value = 1
//    }
//

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (noInternet, progressBar, articleList) = createRefs()

        when (jobState.value) {

            1 -> {
                CircularProgressIndicator(modifier = Modifier
                    .size(40.dp)
                    .constrainAs(progressBar) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            }

            2 -> {
                ArticlePagingList(modifier = Modifier
                    .constrainAs(articleList) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    } ,

                    lazyPagingData = lazyPagingData

                    )
            }

            3 -> {

                Column(modifier = Modifier
                    .constrainAs(noInternet) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = 80.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(id = R.drawable.ic_no_article),
                        contentDescription = null
                    )
                    Text(
                        text = "مقاله ای برای نمایش وجود ندارد",
                        style = MaterialTheme.typography.h5,
                        color = cText2
                    )
                }

            }

        }

    }

    // TODO: work on Snack Bar
    if (!internetConnected) {
        Snackbar(
            modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth() ,
                text = "لطفا از اتصال دستگاه خود به اینترنت مطمئن شوید",
                textAlign = TextAlign.Center
            )
        }

    }

}


