package com.example.newsapp.composobles

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.model.Article
import com.example.newsapp.viewmodels.bookmarkviewmodel

@Composable
fun bookmarks(model:bookmarkviewmodel = hiltViewModel(),onclick: (String?, String?) -> Unit) {
    val state by model.showmydata().observeAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        
        Text(text = "Bookmark", modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold), color = colorResource(
            id =R.color.text_title
        ))

        state?.let {
            bookmarklist(arts = it, onclick)


            }
        }
        Log.d("book",state.toString())

}

@Composable
fun Mybookmark(article: Article, onclick:(String?,String?)->Unit) {
    val sh: SharedPreferences = LocalContext.current.getSharedPreferences("news",
        Context.MODE_PRIVATE
    )
    val edit : SharedPreferences.Editor = sh.edit()
    Row(modifier = Modifier
        .padding(horizontal = 10.dp, vertical = 20.dp)
        .clickable {
            onclick(
                article.title ?: "no title available",
                article.content ?: "no content available"
            )
            edit.putString("url", article.url)
            edit.putString("img", article.urlToImage)
            edit.apply()

        }) {
        SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(article.urlToImage).build(),
            contentDescription = article.description,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))

            })
        Column(
            modifier = Modifier.padding(start = 8.dp, top = 5.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                text = article.description ?: "no description ",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                fontWeight = FontWeight.Normal
            )



            Row(modifier = Modifier.padding(top = 20.dp)) {
                Text(
                    text = article.source.name ?: "no source available",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
                Icon(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 3.dp)
                        .size(15.dp),
                    painter = painterResource(id = R.drawable.baseline_access_alarms_24),
                    tint =
                    colorResource(id = R.color.text_title),
                    contentDescription = "alarm"
                )
                Text(
                    text = article.publishedAt ?: "no date available",
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
            }


        }
    }
}

@Composable
fun bookmarklist(arts:List<Article>, onclick:(String?,String?)->Unit) {
    LazyColumn {
        items(arts) {
            Mybookmark(it, onclick)

        }
    }
}