package com.example.movieapp.Domain

object WatchlistManager {
    private val watchlist = mutableListOf<FilmItemModel>()

    fun addToWatchlist(movie: FilmItemModel) {
        if (!watchlist.contains(movie)) {
            watchlist.add(movie)
        }
    }

    fun removeFromWatchlist(movie: FilmItemModel) {
        watchlist.remove(movie)
    }
    fun clearWatchlist() {
        watchlist.clear()
    }

    fun getWatchlist(): List<FilmItemModel> = watchlist
}
