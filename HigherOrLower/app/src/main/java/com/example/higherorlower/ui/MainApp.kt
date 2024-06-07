package com.example.higherorlower.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.higherorlower.ui.MovieScreen.MoviePhotosApp
import com.example.higherorlower.ui.screens.HomeScreen
import com.example.higherorlower.ui.screens.AboutScreen
import com.example.higherorlower.ui.screens.CategoriesScreen
import com.example.higherorlower.ui.screens.FeedbackScreen

enum class Screen (route: String) {
    Home("home"),
    About("about"),
    Feedback("feedback"),
    Categories("categories"),
    Movie("movie");
}


@ExperimentalMaterial3Api
@Composable
fun MainAppBar(
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar (
        title = { Text("Higher or Lower") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(navController: NavHostController = rememberNavController()) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Home.name
    )

    Scaffold(
        topBar = {
            MainAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = Screen.Home.name) {
                HomeScreen(
                    onPlayButtonClicked = { navController.navigate(Screen.Categories.name) },
                    onAboutButtonClicked = { navController.navigate(Screen.About.name) },
                    onFeedbackButtonClicked =  { navController.navigate(Screen.Feedback.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
            composable(route = Screen.About.name) {
                AboutScreen()
            }
            composable(route = Screen.Categories.name) {
                CategoriesScreen(
                    onClickMovie = {
                        navController.navigate(Screen.Movie.name)
                    }
                )
            }
            composable(route = Screen.Feedback.name) {
                FeedbackScreen()
            }
            composable(route = Screen.Movie.name) {
                MoviePhotosApp()
            }

        }
    }
}