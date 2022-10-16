package com.example.librarymanagement.data

import com.example.librarymanagement.model.BookItem

object DataSource {
    val bookingOptions = listOf(
        BookItem.BookingOption(
            name = "BUY",
            description = "Click here to buy the book",
            price = 200.0
        ),
        BookItem.BookingOption(
            name = "Borrow_1",
            description = "Click here to borrow for 30 days",
            price = 50.0
        ),
        BookItem.BookingOption(
            name = "Borrow_2",
            description = "Click here to borrow for 60 days",
            price = 110.0
        ),
        BookItem.BookingOption(
            name = "Borrow_3",
            description = "Click here to borrow for 120 days",
            price = 230.0
        ),
    )
    val giftOption = listOf(
        BookItem.GiftOption(
            name = "Gift",
            description = "Click here to wrap it up in a standard gift packing",
            price = 20.0
        ),
        BookItem.GiftOption(
            name = "Premium Gift",
            description = "Click here to wrap it up in a premium gift packing with Flowers",
            price = 50.0
        ),
        BookItem.GiftOption(
            name = "Personal",
            description = "Click here to opt for no gift Wrapping",
            price = 0.0
        )
    )
}