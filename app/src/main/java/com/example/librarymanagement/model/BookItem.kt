package com.example.librarymanagement.model

import java.text.NumberFormat

sealed class BookItem (
    open val name: String,
    open val description: String,
    open val price: Double,
){
    data class BookingOption(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : BookItem(name,description,price)
    data class giftOption(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : BookItem(name,description,price)
    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)

}