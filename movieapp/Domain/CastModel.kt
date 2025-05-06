package com.example.movieapp.Domain

import java.io.Serializable

data class CastModel(
    var Actor: String = "",
    var PicResId: Int
): Serializable

