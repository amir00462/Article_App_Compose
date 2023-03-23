package ir.dunijet.article_app_compose.ui.wiegets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ir.dunijet.article_app_compose.R
import ir.dunijet.article_app_compose.data.Article
import ir.dunijet.article_app_compose.ui.theme.cText1
import ir.dunijet.article_app_compose.ui.theme.cText3
import ir.dunijet.article_app_compose.ui.theme.radius4
import ir.dunijet.article_app_compose.util.listArticle

@Composable
fun ArticleList(modifier :Modifier) {

    Column(modifier= modifier) {

        Article(listArticle[0]) {

        }

        Article(listArticle[1]) {

        }

        Article(listArticle[2]) {

        }

        Article(listArticle[3]) {

        }

    }

}

@Composable
fun Article(article: Article, onClicked: (Article) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp , bottom = 8.dp)
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClicked.invoke(article)
        }) {

        val (category, pic, title) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(category) {
                    top.linkTo(pic.top)
                    start.linkTo(pic.end)
                }
                .padding(start = 18.dp , top = 8.dp),
            text = article.category,
            color = cText3,
            style = MaterialTheme.typography.overline
        )

        Text(
            modifier = Modifier.width(260.dp)
                .constrainAs(title) {
                    top.linkTo(category.bottom)
                    start.linkTo(pic.end)
                }
                .padding(start = 18.dp, top = 2.dp , end = 16.dp),
            text = article.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis ,
            color = cText1,
            style = MaterialTheme.typography.h5
        )

        // change it with coil image :)
        Image(
            modifier = Modifier
                .size(120.dp, 90.dp)
                .clip(radius4)
                .constrainAs(pic) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 16.dp),
            painter = painterResource(id = R.drawable.pic1),
            contentDescription = null
        )


    }

}