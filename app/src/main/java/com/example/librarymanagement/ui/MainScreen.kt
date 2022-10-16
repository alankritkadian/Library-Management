package com.example.librarymanagement.ui

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.librarymanagement.data.Book
import com.example.librarymanagement.data.books
import com.example.librarymanagement.R
import kotlin.math.exp

@Composable
fun MainScreen(
    onBookButtonClicked : () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold {
        LazyColumn (
            modifier = Modifier.background(MaterialTheme.colors.background)
        ){
            items(books) {
                BookItem(book = it,onBookButtonClicked = onBookButtonClicked)
            }
        }
    }
}

@Composable
fun BookItem(book: Book, modifier: Modifier = Modifier,onBookButtonClicked : () -> Unit){
    var expanded by remember{ mutableStateOf(false)}
    Card (
        modifier = modifier.padding(8.dp),
        elevation = 4.dp
    ){
        Column(
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                BookIcon(book.imageResourceId)
                BookInformation(book.name, book.copies)
                Spacer(modifier = modifier.weight(1f))
                BookItemButton(onClick = { expanded = !expanded }, expanded = expanded)
            }
            if (expanded) {
                BookDes(book.description,onBookButtonClicked = onBookButtonClicked)
            }
        }
    }
}
@Composable
fun BookItemButton(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    expanded: Boolean
){
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colors.secondary,
            contentDescription = stringResource(R.string.expand_button)
        )
    }

}
@Composable
fun BookDes(
    @StringRes bookDes: Int,
    modifier: Modifier = Modifier,
    onBookButtonClicked : () -> Unit,
){
    Column (
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ){
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(bookDes),
            style = MaterialTheme.typography.body1
        )
        Button(
            onClick = onBookButtonClicked,
        ){
            Text(stringResource(id = R.string.borrow))
        }
        

    }
}
@Composable
fun BookIcon(
    @DrawableRes bookIcon: Int,
    modifier: Modifier = Modifier
){
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp),
        painter = painterResource(bookIcon),
        contentDescription = null,
//        contentScale = ContentScale.Crop
    )
}

@Composable
fun BookInformation(
    @StringRes bookName: Int,
    bookCopies: Int,
    modifier: Modifier = Modifier,
){
    Column {
        Text(
            text = stringResource(bookName),
            modifier = modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.h2,
        )
        Text(
            text = stringResource(R.string.copies_available,bookCopies),
            style = MaterialTheme.typography.body1
        )
    }
}