package com.example.movieapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.Domain.FilmItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()


    fun loadUpcoming(): LiveData<MutableList<FilmItemModel>> {
        val listData = MutableLiveData<MutableList<FilmItemModel>>()
        val ref = firebaseDatabase.getReference("Upcoming")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FilmItemModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(FilmItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
                println("Error: ${error.message}")
            }
        })
        return listData
    }

    // Function to load other movies (Change "Movies" to your actual Firebase node)
    fun loadItems(): LiveData<MutableList<FilmItemModel>> {
        val listData = MutableLiveData<MutableList<FilmItemModel>>()
        val ref = firebaseDatabase.getReference("item")  // Change this if needed

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FilmItemModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(FilmItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error: ${error.message}")
            }
        })
        return listData
    }
}
