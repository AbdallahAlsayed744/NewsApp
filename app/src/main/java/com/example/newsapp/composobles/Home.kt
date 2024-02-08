package com.example.newsapp.composobles

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.model.Article
import com.example.newsapp.model.Source
import com.example.newsapp.viewmodels.homeviewmodel
import com.example.newsapp.viewmodels.mainviewmodel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun Home(onclick:(String?,String?)->Unit,model: homeviewmodel = hiltViewModel()) {
    val state by model.headlinesdata.collectAsState()

    val isLoading by model.isLoading.collectAsState()

    var isNetworkAvailable by remember { mutableStateOf(true) }

    var retryAttempted by remember { mutableStateOf(false) }

    isNetworkAvailable = isNetworkAvailable(context = LocalContext.current)

    Column(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Mylogo",
            modifier = Modifier,
            alignment = Alignment.TopStart
        )

        Mysearch(hint = "Search..", onsearch = {})

        if (isLoading){
            CircularProgressIndicator(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp))
        }



        if (isNetworkAvailable) {

            mynwesonlazy(arr = state, onclick)


        }
        else{
            RetrySection(error = "No Internet Avilable") {
                retryAttempted = true

            }

        }

    }

    if (retryAttempted) {
        LaunchedEffect(retryAttempted) {
            retryAttempted = false
        }

    }


}

@Composable
fun Mysearch(modifier: Modifier = Modifier, hint: String, onsearch: (String) -> Unit) {

    var text by rememberSaveable {
        mutableStateOf("")
    }

    Box(modifier = modifier.padding(horizontal = 10.dp)) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(26.dp))
                .clip(AbsoluteRoundedCornerShape(20.dp)),
            value = text,
            placeholder = {
                Text(text = hint)
            },
            onValueChange = {
                text = it
                onsearch(it)
            },
            maxLines = 1,
            prefix = {
                Icon(Icons.Outlined.Search, contentDescription = "search")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }

}

@SuppressLint("CommitPrefEdits")
@Composable
fun Mynesw(article: Article,onclick:(String?,String?)->Unit) {

    val sh:SharedPreferences= LocalContext.current.getSharedPreferences("news", MODE_PRIVATE)
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
            edit.putString("sourceid", article.source.id)
            edit.putString("sourcename", article.source.name)

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
                text = article.description ?: "no description available",
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
fun mynwesonlazy(arr: List<Article>,onclick:(String?,String?)->Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(arr) {
            Mynesw(it,onclick)

        }

    }

}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.wifi_connenction), modifier = Modifier.size(150.dp), contentDescription ="" )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = colorResource(id = R.color.text_title)
            )
        ) {
            Text(text = "Retry")
        }
    }
}
@Composable
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities != null &&
                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}


