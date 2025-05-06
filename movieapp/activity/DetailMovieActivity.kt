
package com.example.movieapp.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.Domain.FilmItemModel
import com.example.movieapp.Domain.WatchlistManager
import com.example.movieapp.R


class DetailMovieActivity : BaseActivity() {
    private lateinit var filmItem:FilmItemModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        filmItem = intent.getSerializableExtra("object") as FilmItemModel

        setContent {
            DetailScreen(filmItem, onBackClick = {finish()})
        }
    }
}

@Composable
fun DetailScreen(film:FilmItemModel,onBackClick:()->Unit){
    val isAdded = remember { mutableStateOf(false) } // ✅ Track if movie is added
    val context = LocalContext.current
    val scrollState= rememberScrollState()
    val isLoading= remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color= colorResource(R.color.blackBackground))
    ){
        if (isLoading.value){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }else{
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ){
                Box(
                    modifier = Modifier.height(400.dp)
                ){
                    Image(
                        contentDescription = "",
                        painter = painterResource(R.drawable.back),
                        modifier = Modifier
                            .padding(start=16.dp,top=48.dp)
                            .clickable{onBackClick()}
                    )
                    Button(
                        onClick = {
                            if (!isAdded.value) {
                                WatchlistManager.addToWatchlist(film)
                                isAdded.value = true
                                Toast.makeText(context, "Added to Watchlist", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Already in Watchlist", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 16.dp, top = 48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE50914))
                    ) {
                        Text("Watchlist", color = Color.White)
                    }
                    AsyncImage(
                        model=film.Poster,
                        contentDescription=null,
                        modifier = Modifier
                            .size(210.dp,300.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .align(Alignment.BottomCenter),
                        contentScale=ContentScale.Crop
                    )
                    Box(modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    colorResource(R.color.black2),
                                    colorResource(R.color.black1)
                                ), start = Offset(0f,0f),
                                end = Offset(0f,Float.POSITIVE_INFINITY)
                            )
                        )
                    )
                    Text(
                        text = film.Title,
                        style = TextStyle(color = Color.White, fontSize = 27.sp),
                        modifier = Modifier
                            .padding(end=16.dp, top = 48.dp)
                            .align(Alignment.BottomCenter)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column (modifier = Modifier.padding(horizontal = 16.dp)){
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(R.drawable.imdb)
                            , contentDescription = null,
                            tint = Color.Unspecified,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(text = "IMDB:${film.Imdb}", color = Color.White)
                        Icon(
                            painter = painterResource(R.drawable.time)
                            , contentDescription = null,
                            tint=Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(text = "Runtime:${film.Time}", color = Color.White)
                        Icon(
                            painter = painterResource(R.drawable.cal)
                            , contentDescription = null,
                            tint=Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(text = "Release:${film.Year}", color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Summary",
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(film.Description,
                        style = TextStyle(color = Color.White, fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Text("Actors",
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(contentPadding = PaddingValues(8.dp)) {
                        items(film.Casts.size) {
                            Text(
                                text = "${film.Casts[it].Actor},",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyRow (contentPadding = PaddingValues(8.dp)){
                        items(count = film.Casts.size) { i ->  // ✅ Use 'i' as the index
                            Image(
                                painter = painterResource(id = film.Casts[i].PicResId),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(75.dp)
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(50.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Text("Available On", style = TextStyle(color = Color.White, fontSize = 16.sp))
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(contentPadding = PaddingValues(8.dp)) {
                        items(film.streamingPlatforms.size) { i ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(end = 12.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = film.streamingPlatforms[i].iconResId),
                                    contentDescription = film.streamingPlatforms[i].name,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = film.streamingPlatforms[i].name,
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                }

            }

        }
    }
}







