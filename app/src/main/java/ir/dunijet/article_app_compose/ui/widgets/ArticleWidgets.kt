package ir.dunijet.article_app_compose.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import ir.dunijet.article_app_compose.R
import ir.dunijet.article_app_compose.data.model.Article
import ir.dunijet.article_app_compose.ui.theme.cText1
import ir.dunijet.article_app_compose.ui.theme.cText3
import ir.dunijet.article_app_compose.ui.theme.radius4
import ir.dunijet.article_app_compose.util.mockArticle

@Composable
fun ArticlePagingList(modifier: Modifier, lazyPagingData: LazyPagingItems<Article>) {

    LazyColumn(modifier = modifier) {

        items(items = lazyPagingData) {
            Article(article = it!!) {

            }
        }

        if (lazyPagingData.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(12.dp)
                )
            }
        }

    }

}

@Composable
fun Article(article: Article, onClicked: (Article) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 8.dp)
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
                .padding(start = 18.dp, top = 8.dp),
            text = article.category,
            color = cText3,
            style = MaterialTheme.typography.overline
        )

        Text(
            modifier = Modifier
                .width(260.dp)
                .constrainAs(title) {
                    top.linkTo(category.bottom)
                    start.linkTo(pic.end)
                }
                .padding(start = 18.dp, top = 2.dp, end = 16.dp),
            text = article.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = cText1,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Start
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
