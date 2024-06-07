package com.example.higherorlower.ui.MovieScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.higherorlower.R
import com.example.higherorlower.model.MoviePhoto
import com.example.higherorlower.ui.theme.HigherOrLowerTheme

@Composable
fun MovieScreen(
    movieUiState: MovieUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (movieUiState) {
        is MovieUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MovieUiState.Success -> MoviePhotoCard(photos = movieUiState.photos, viewModel = MovieViewModel(), modifier = modifier.fillMaxSize())
        is MovieUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ResultScreen(photos: List<MoviePhoto>, modifier: Modifier = Modifier) {
    val photo: String = photos.size.toString()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photo)
    }
}

@Composable
fun MoviePhotoCard(photos: List<MoviePhoto>, viewModel: MovieViewModel, modifier: Modifier = Modifier) {
   Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       val photo1 = viewModel.photo1
       val photo2 = viewModel.photo2
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo1.image)
                .crossfade(true)
                .build(),
            contentDescription = photo1.description,
            modifier = Modifier
                .clickable {
                    if (photo1.rating >= photo2.rating) {
                        viewModel.updateVariablesOnClick(photos)
                    }
                }
                .padding(bottom = 20.dp)
                .padding(top = 40.dp)
                .size(300.dp)
                .height(300.dp)
        )
        Text(
            text = "Rating prvog filma je ${photo1.rating}"
        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo2.image)
                .crossfade(true)
                .build(),
            contentDescription = photo2.description,
            modifier = Modifier
                .clickable {
                    if (photo2.rating >= photo1.rating) {
                        viewModel.updateVariablesOnClick(photos)
                    }
                }
                .padding(top = 20.dp)
                .size(300.dp)
                .height(300.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    HigherOrLowerTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    HigherOrLowerTheme {
        ErrorScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    HigherOrLowerTheme {
        ResultScreen(listOf())
    }
}
