package com.example.movieapp.Domain

import java.io.Serializable

data class FilmItemModel(
    var Title:String="",
    var Description:String="",
    var Poster:Int,
    var Time:String="",
    var Trailer:String="",
    var Imdb:Double=0.0,
    var Year:Int=0,
    var price:Double=0.0,
    var Genre:String="",
    var Casts:List<CastModel>,
    val streamingPlatforms: List<StreamingPlatformModel>

):Serializable
data class StreamingPlatformModel(
    val name: String,
    val iconResId: Int
) : Serializable