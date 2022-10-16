package com.example.librarymanagement.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.librarymanagement.R

data class Book(
    @DrawableRes val imageResourceId : Int,
    @StringRes val name : Int,
    val copies: Int,
    @StringRes val description: Int,
)

val books = listOf(
    Book(R.drawable.sherlock,R.string.book_1,7,R.string.book_des_1),
    Book(R.drawable.harry_potter,R.string.book_2,4,R.string.book_des_2),
    Book(R.drawable.lotr,R.string.book_3,5,R.string.book_des_3),
    Book(R.drawable.story_of_my_life,R.string.book_4,9,R.string.book_des_4)
)