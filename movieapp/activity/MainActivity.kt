package com.example.movieapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.BottomNavigationBar
import com.example.movieapp.Domain.CastModel
import com.example.movieapp.Domain.FilmItemModel
import com.example.movieapp.Domain.StreamingPlatformModel
import com.example.movieapp.R
import com.example.movieapp.SearchBar
import com.example.movieapp.ViewModel.FilmItem

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
MainScreen(onItemClick = {item->
val intent=Intent(this,DetailMovieActivity::class.java)
    intent.putExtra("object",item)
    startActivity(intent)
})
        }
    }
}
@Preview
@Composable
fun MainScreen(onItemClick:(FilmItemModel)->Unit={}){
    Scaffold(bottomBar = { BottomNavigationBar() },

    ){paddingValues ->
        Box(
            modifier=Modifier
                .padding(paddingValues)
                .background(color = colorResource(R.color.blackBackground))
        )
        {
            Image(
                painter= painterResource(id=R.drawable.bg1),
                contentDescription=null,
                contentScale=ContentScale.Crop,
                modifier=Modifier.matchParentSize()
            )
            MainContent(onItemClick)
        }

    }
}

@Composable
fun MainContent(onItemClick: (FilmItemModel) -> Unit) {
    val upcoming = listOf(
        FilmItemModel(
            Title = "Fly Me to the Moon",
            Poster = R.drawable.fly_me_to_the_moon,
            Description = "Marketing maven Kelly Jones wreaks havoc on launch director Cole Davis's already difficult task. When the White House deems the mission too important to fail, the countdown truly begins.",
            Genre = "Rom-Com",
            Imdb = 6.6,
            Time = "2h 12m",
            Trailer = "https://www.imdb.com/video/vi2956707353/?playlistId=tt1896747&ref_=tt_ov_vi",
            Year = 2024,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "Atlas",
            Poster = R.drawable.atlas,
            Description = "In a bleak-sounding future, an A.I. soldier has determined that the only way to end war is to end humanity.",
            Genre = "Sci-Fi",
            Imdb = 5.6,
            Time = "1h 58m",
            Trailer = "https://www.imdb.com/video/vi1105249817/?playlistId=tt14856980&ref_=tt_pr_ov_vi",
            Year = 2024,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),

        FilmItemModel(
            Title = "The Three Musketeers",
            Poster = R.drawable.three_musketern,
            Description = "A young man named D'Artagnan travels to Paris to become a musketeer and joins forces with Athos, Porthos, and Aramis to stop a plot to overthrow the king.",
            Genre = "Adventure",
            Imdb = 8.2,
            Time = "2h 7m",
            Trailer = "https://www.youtube.com/watch?v=FsuJMqePZtg",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),

        FilmItemModel(
            Title = "Immaculate",
            Poster = R.drawable.immaculate,
            Description = "A young woman who possesses the ability to heal others must fight to protect her gift from those who would exploit it.",
            Genre = "Thriller",
            Imdb = 8.3,
            Time = "1h 36m",
            Trailer = "https://www.youtube.com/watch?v=ewxS9Z-XXYo",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "Damaged",
            Poster = R.drawable.damaged,
            Description = "A war veteran struggling with PTSD seeks revenge on the people who killed his family.",
            Genre = "Action",
            Imdb = 8.6,
            Time = "1h 54m",
            Trailer = "https://www.youtube.com/watch?v=Sg8NuJL5P_8",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),

        FilmItemModel(
            Title = "Rebel Moon",
            Poster = R.drawable.rebel_moon,
            Description = "A young woman with a mysterious past is tasked with uniting different tribes to fight against an oppressive warlord who threatens their peaceful colony.",
            Genre = "Sci-Fi",
            Imdb = 9.0,
            Time = "2h 22m",
            Trailer = "https://www.youtube.com/watch?v=UEJuNHOd8Dw",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),

        FilmItemModel(
            Title = "Godzilla-Kong",
            Poster = R.drawable.godzila,
            Description = "Godzilla and Kong must team up to battle a common enemy that threatens to destroy both of their worlds.",
            Genre = "Action",
            Imdb = 8.5,
            Time = "1h 56m",
            Trailer = "https://www.youtube.com/watch?v=qqrpMRDuPfc",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),

        FilmItemModel(
            Title = "No Way Up",
            Poster = R.drawable.no_way_up,
            Description = "A group of strangers trapped in an elevator must find a way to escape before they run out of air.",
            Genre = "Thriller",
            Imdb = 7.5,
            Time = "1h 38m",
            Trailer = "https://www.youtube.com/watch?v=PSEoAaSswgo",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),

        FilmItemModel(
            Title = "Kung Fu Panda 4",
            Poster = R.drawable.kung_fo_panda,
            Description = "Po and the Furious Five must once again defend the Jade Palace from a new threat.",
            Genre = "Animation",
            Imdb = 7.5,
            Time = "1h 42m",
            Trailer = "https://www.youtube.com/watch?v=_inKs4eeHiI",
            Year = 2024,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        )
    )
    var searchQuery by remember { mutableStateOf("") }

    val newMovies = listOf(
        FilmItemModel(
            Title = "Bad Boys",
            Poster = R.drawable.bad_boys,
            Description = "When their late police captain gets linked to drug cartels, wisecracking Miami cops Mike Lowrey and Marcus Burnett embark on a dangerous mission to clear his name.",
            Genre = "Action",
            Imdb = 6.6,
            Time = "1h 55m",
            Trailer = "https://www.imdb.com/video/vi3000682009/?playlistId=tt4919268&ref_=tt_ov_vi",
            Year = 2024,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "The Fall Guy",
            Poster = R.drawable.the_fall_guy,
            Description = "A stuntman, fresh off an almost career-ending accident, has to track down a missing movie star, solve a conspiracy and try to win back the love of his life while still doing his day job. What could possibly go right?",
            Genre = "Action",
            Imdb = 6.9,
            Time = "2h 6m",
            Trailer = "https://www.imdb.com/video/vi1681377049/?ref_=ttvi_vi_imdb_21",
            Year = 2024,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "Rebel Moon",
            Poster = R.drawable.rebel_moon,
            Description = "A young woman with a mysterious past is tasked with uniting different tribes to fight against an oppressive warlord who threatens their peaceful colony.",
            Genre = "Sci-Fi",
            Imdb = 9.0,
            Time = "2h 22m",
            Trailer = "https://www.youtube.com/watch?v=UEJuNHOd8Dw",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "Immaculate",
            Poster = R.drawable.immaculate,
            Description = "A young woman who possesses the ability to heal others must fight to protect her gift from those who would exploit it.",
            Genre = "Mystery",
            Imdb = 8.3,
            Time = "1h 36m",
            Trailer = "https://www.youtube.com/watch?v=ewxS9Z-XXYo",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "Godzilla-Kong",
            Poster = R.drawable.godzila,
            Description = "Godzilla and Kong must team up to battle a common enemy that threatens to destroy both of their worlds.",
            Genre = "Adventure",
            Imdb = 8.5,
            Time = "1h 56m",
            Trailer = "https://www.youtube.com/watch?v=qqrpMRDuPfc",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "No Way Up",
            Poster = R.drawable.no_way_up,
            Description = "A group of strangers trapped in an elevator must find a way to escape before they run out of air.",
            Genre = "Thriller",
            Imdb = 7.5,
            Time = "1h 38m",
            Trailer = "https://www.youtube.com/watch?v=PSEoAaSswgo",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "Kung Fu Panda 4",
            Poster = R.drawable.kung_fo_panda,
            Description = "Po and the Furious Five must once again defend the Jade Palace from a new threat.",
            Genre = "Animation",
            Imdb = 7.5,
            Time = "1h 42m",
            Trailer = "https://www.youtube.com/watch?v=_inKs4eeHiI",
            Year = 2024,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "The Three Musketeers",
            Poster = R.drawable.three_musketern,
            Description = "A young man named D'Artagnan travels to Paris to become a musketeer and joins forces with Athos, Porthos, and Aramis to stop a plot to overthrow the king.",
            Genre = "Historical",
            Imdb = 8.2,
            Time = "2h 7m",
            Trailer = "https://www.youtube.com/watch?v=FsuJMqePZtg",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        ),
        FilmItemModel(
            Title = "Damaged",
            Poster = R.drawable.damaged,
            Description = "A war veteran struggling with PTSD seeks revenge on the people who killed his family.",
            Genre = "Crime",
            Imdb = 8.6,
            Time = "1h 54m",
            Trailer = "https://www.youtube.com/watch?v=Sg8NuJL5P_8",
            Year = 2023,
            price = 120.0,
            streamingPlatforms = listOf(
                StreamingPlatformModel("Netflix", R.drawable.netflix),
                StreamingPlatformModel("Prime", R.drawable.prime),
                StreamingPlatformModel("Youtube", R.drawable.youtube)
            ),
            Casts = listOf(
                CastModel("Scarlett Johansson", R.drawable.scarlett_johansson),
                CastModel("Channing Tatum", R.drawable.channing_tatum),
                CastModel("Woody Harrelson", R.drawable.woody_harrelson),
                CastModel("Ray Romano", R.drawable.ray_romano),
            )
        )
    )

    val genres = listOf("All", "Action", "Animation", "Rom-Com", "Crime")
    var selectedGenre by remember { mutableStateOf("All") }

    val allMovies = newMovies + upcoming
    val filteredMovies = allMovies.filter {
        val matchesSearch = it.Title.contains(searchQuery, ignoreCase = true)
        val matchesGenre = selectedGenre == "All" || it.Genre.equals(selectedGenre, ignoreCase = true)
        matchesSearch && matchesGenre
    }
    val isSearching = searchQuery.isNotBlank()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 60.dp, bottom = 100.dp)
    ) {
        Text(
            text = "What would you like to watch?",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        SearchBar(hint = "Search Movies...",
            query = searchQuery,
            onQueryChanged = { searchQuery = it })
        if (isSearching) {
            if (filteredMovies.isEmpty()) {
                Text(
                    text = "No movies found for \"$searchQuery\"",
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                LazyRow(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(filteredMovies) { item ->
                        FilmItem(item, onItemClick)
                    }
                }
            }
        }


        SectionTitle("New Movies")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(newMovies) { item ->
                FilmItem(item, onItemClick)
            }
        }

        SectionTitle("Upcoming Movies")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(upcoming) { item ->
                FilmItem(item, onItemClick)
            }
        }

        SectionTitle("Filter by Genre:$selectedGenre")
        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(genres) { genre ->
                Box(
                    modifier = Modifier
                        .background(
                            color = if (selectedGenre == genre) Color(0xffffc107) else Color.Gray,
                            shape = CircleShape
                        )
                        .clickable { selectedGenre = genre }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = genre, color = Color.Black)
                }
            }
        }

        SectionTitle("Filtered by Genre: $selectedGenre")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(filteredMovies) { item ->
                FilmItem(item, onItemClick)
            }
        }


    }
}

@Composable
fun MovieItem(movie: FilmItemModel, onItemClick: (FilmItemModel) -> Unit) {
    Box(
        modifier = Modifier
            .padding(end = 12.dp)
            .size(width = 160.dp, height = 250.dp)
            .background(color = Color.DarkGray, shape = CircleShape)
            .padding(8.dp)
            .clickable { onItemClick(movie) }
    ) {
        Image(
            painter = painterResource(id = movie.Poster),
            contentDescription = movie.Title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun SectionTitle(title: String){
    Text(
        text = title,
        style = TextStyle(color = Color(0xffffc107), fontSize = 18.sp),
        modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 8.dp),
        fontWeight = FontWeight.Bold
    )
}

