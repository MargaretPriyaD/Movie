package com.example.movieapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.BottomNavigationBar
import com.example.movieapp.Domain.WatchlistManager
import com.example.movieapp.R

class WatchlistActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchlistScreen()
        }
    }
}
@Composable
fun WatchlistScreen() {
    val context = LocalContext.current
    val watchlist = remember { mutableStateListOf(*WatchlistManager.getWatchlist().toTypedArray()) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.blackBackground))
        ) {
            // 🌄 Background Image
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            if (watchlist.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No items added yet", color = Color.White, fontSize = 18.sp)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(watchlist.size) { i ->
                        val film = watchlist[i]
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    val intent = Intent(context, DetailMovieActivity::class.java)
                                    intent.putExtra("object", film)
                                    context.startActivity(intent)
                                }
                        ) {
                            AsyncImage(
                                model = film.Poster,
                                contentDescription = film.Title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(80.dp, 100.dp)
                                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        val intent = Intent(context, DetailMovieActivity::class.java)
                                        intent.putExtra("object", film)
                                        context.startActivity(intent)
                                    }
                            ) {
                                Text(film.Title, color = Color.White, fontWeight = FontWeight.Bold)
                                Text("Year: ${film.Year}", color = Color.LightGray)
                            }
                            Button(
                                onClick = {
                                    watchlist.remove(film)
                                    WatchlistManager.removeFromWatchlist(film)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                modifier = Modifier.padding(start = 8.dp)
                            ) {
                                Text("Remove", color = Color.White)
                            }
                        }
                    }
                }

                // 🧹 Clear All Button
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 20.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = {
                            watchlist.clear()
                            WatchlistManager.clearWatchlist()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Clear All", color = Color.White)
                    }
                }
            }
        }
    }
}

