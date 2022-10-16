package com.example.librarymanagement.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.librarymanagement.model.BookItem

@Composable
fun BookingScreen(
    options: List<BookItem.BookingOption>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (BookItem.BookingOption) -> Unit,
    modifier: Modifier = Modifier
){
    BaseMenuScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged as (BookItem) -> Unit
    )
}