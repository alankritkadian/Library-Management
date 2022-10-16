package com.example.librarymanagement

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.librarymanagement.data.DataSource
import com.example.librarymanagement.ui.*

enum class LibraryAppScreen(@StringRes val title: Int){
    Start(R.string.app_name),
    Options(R.string.option),
    Gift(R.string.gift),
    Checkout(R.string.checkout)
}

@Composable
fun LibraryAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar (
        title = {Text(stringResource(currentScreenTitle))},
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LibraryApp(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LibraryAppScreen.valueOf(
        backStackEntry?.destination?.route ?: LibraryAppScreen.Start.name
    )
    val viewModel: BookViewModel = viewModel()
    Scaffold (
        topBar = {
            LibraryAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){ innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = LibraryAppScreen.Start.name,
            modifier = modifier.padding(innerPadding),
        ) {
            composable(route = LibraryAppScreen.Start.name){
                MainScreen(
                    onBookButtonClicked = {
                        navController.navigate(LibraryAppScreen.Options.name)
                    }
                )
            }

            composable(route = LibraryAppScreen.Options.name) {
                BookingScreen(
                    options = DataSource.bookingOptions,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LibraryAppScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LibraryAppScreen.Gift.name)
                    },
                    onSelectionChanged = {  item ->
                        viewModel.updateEntree(item)
                    }
                )
            }
            composable(route = LibraryAppScreen.Gift.name) {
                GiftScreen(
                    options = DataSource.giftOption,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LibraryAppScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LibraryAppScreen.Checkout.name)
                    },
                    onSelectionChanged = { item ->
                        viewModel.updateGift(item)
                    }
                )
            }
            composable(route = LibraryAppScreen.Checkout.name) {
                CheckoutScreen(
                    bookUiState = uiState,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LibraryAppScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LibraryAppScreen.Start.name, inclusive = false)
                    }
                )
            }

        }

    }
}