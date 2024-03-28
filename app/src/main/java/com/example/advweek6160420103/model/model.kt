package com.example.advweek6160420103.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val genre: String,
    val characters: List<String>,
    val imageUrl: String
)