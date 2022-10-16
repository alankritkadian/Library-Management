package com.example.librarymanagement.ui

import androidx.lifecycle.ViewModel
import com.example.librarymanagement.model.BookItem
import com.example.librarymanagement.model.BookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class BookViewModel : ViewModel() {
    private val taxRate = 0.05
    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()
    fun updateEntree(entree: BookItem) {
        val previousEntree = _uiState.value.entree
        updateItem(entree, previousEntree)
    }
    fun updateGift(giftOption: BookItem.GiftOption) {
        val previousGift = _uiState.value.GiftChoice
        updateItem(giftOption, previousGift)
    }
    private fun updateItem(newItem: BookItem, previousItem: BookItem?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            // subtract previous item price in case an item of this category was already added.
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            // recalculate tax
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax,
                entree = if (newItem is BookItem.BookingOption) newItem else currentState.entree,
            )
        }
    }
    fun resetOrder() {
        _uiState.value = BookUiState()
    }
}
fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}