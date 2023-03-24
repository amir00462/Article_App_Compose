package ir.dunijet.article_app_compose.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ir.dunijet.article_app_compose.R
import ir.dunijet.article_app_compose.ui.theme.cBackground
import ir.dunijet.article_app_compose.ui.theme.cBlack

@Composable
fun BlogToolbar(titleArticle: String, onBackClicked: () -> Unit, onInfoClicked: () -> Unit) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(cBackground)
    ) {

        val (info, back, title) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 18.dp, end = 16.dp),
            color = cBlack,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h6,
            text = titleArticle

        )

        MainButton(modifier = Modifier.constrainAs(info) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end, 16.dp)
        }, R.drawable.ic_search) {
            onInfoClicked.invoke()
        }

        MainButton(modifier = Modifier.constrainAs(back) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start, 16.dp)
        }, R.drawable.ic_menu) {
            onBackClicked.invoke()
        }

    }

}