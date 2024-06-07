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
@Composable
fun MoviePhotosApp(
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val movieViewModel: MovieViewModel = viewModel()
            MovieScreen(
                movieUiState = movieViewModel.movieUiState,
                contentPadding = innerPadding
            )
        }
    }
}
