package com.example.librarymanagement.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.librarymanagement.model.BookItem
import com.example.librarymanagement.model.BookUiState
import com.example.librarymanagement.R
@Composable
fun CheckoutScreen(
    bookUiState: BookUiState,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Order Summary",
            fontWeight = FontWeight.Bold
        )
        ItemSummary(item = bookUiState.entree)
        Divider(thickness = 1.dp, modifier = Modifier.padding(bottom = 8.dp))
        OrderSubCost(
            resourceId = R.string.subtotal,
            price = bookUiState.itemTotalPrice.formatPrice(),
            Modifier.align(Alignment.End)
        )
        OrderSubCost(
            resourceId = R.string.tax,
            price = bookUiState.orderTax.formatPrice(),
            Modifier.align(Alignment.End)
        )

        Text(
            text = stringResource(R.string.total, bookUiState.orderTotalPrice.formatPrice()),
            modifier = Modifier.align(Alignment.End),
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text("Cancel")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNextButtonClicked
            ) {
                Text("Submit")
            }
        }
    }
}

@Composable
fun ItemSummary(
    item: BookItem?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(item?.name ?: "")
        Text(item?.getFormattedPrice() ?: "")
    }
}

@Composable
fun OrderSubCost(
    @StringRes resourceId: Int,
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(resourceId, price),
        modifier = modifier
    )
}