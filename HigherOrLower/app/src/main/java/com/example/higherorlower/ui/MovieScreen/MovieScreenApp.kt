@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.higherorlower.ui.MovieScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.higherorlower.R
import com.example.higherorlower.ui.utils.MovieContentType
import com.example.higherorlower.ui.utils.MovieNavigationType
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
@Composable
fun MoviePhotosApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navigationType: MovieNavigationType
    val contentType: MovieContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = MovieNavigationType.BOTTOM_NAVIGATION
            contentType = MovieContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = MovieNavigationType.NAVIGATION_RAIL
            contentType = MovieContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = MovieNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = MovieContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = MovieNavigationType.BOTTOM_NAVIGATION
            contentType = MovieContentType.LIST_ONLY
        }
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MovieTopAppBar(scrollBehavior = scrollBehavior) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val movieViewModel: MovieViewModel = viewModel()
            MovieScreen(
                navigationType = navigationType,
                contentType = contentType,
                movieUiState = movieViewModel.movieUiState,
                contentPadding = innerPadding
            )
        }
    }
}

@Composable
fun MovieTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}
