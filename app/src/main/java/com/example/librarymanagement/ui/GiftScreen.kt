package com.example.librarymanagement.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.librarymanagement.data.DataSource
import com.example.librarymanagement.model.BookItem

@Composable
fun GiftScreen(
    options: List<BookItem.giftOption>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (BookItem.giftOption) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged as (BookItem) -> Unit
    )
}
