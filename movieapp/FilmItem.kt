package com.example.movieapp.ViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.movieapp.Domain.FilmItemModel
import com.example.movieapp.R

@Composable
fun FilmItem(item:FilmItemModel,onItemClick:(FilmItemModel)->Unit){
    Column (
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .clickable { onItemClick(item) }
            .background(color = Color("#2f2f39".toColorInt()))
    )
    {
        Image(
            painter = painterResource(id = item.Poster),
            contentDescription = item.Title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = item.Title,
            modifier = Modifier.padding(start = 4.dp),
            style = TextStyle(color = Color.White, fontSize = 11.sp),
            maxLines = 1)
        Spacer(modifier = Modifier.height(4.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.imdb),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = item.Imdb.toString(), style = TextStyle(color = Color.White, fontSize = 12.sp))
        }
    }
}
