package com.example.librarymanagement.model

data class BookUiState (
    val entree: BookItem.BookingOption? = null,
    val itemTotalPrice: Double = 0.0,
    val GiftChoice: BookItem.giftOption? = null,
    val orderTotalPrice: Double = 0.0,
    val orderTax: Double = 0.0,
)