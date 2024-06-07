package com.example.higherorlower.ui.MovieScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
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
import com.example.higherorlower.ui.utils.MovieContentType
import com.example.higherorlower.ui.utils.MovieNavigationType

@Composable
fun MovieScreen(
    navigationType: MovieNavigationType,
    contentType: MovieContentType,
    movieUiState: MovieUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    if(contentType == MovieContentType.LIST_AND_DETAIL){
        when (movieUiState) {
            is MovieUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is MovieUiState.Success -> MoviePhotoCardRow(photos = movieUiState.photos, viewModel = MovieViewModel(), modifier = modifier.fillMaxSize())
            is MovieUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }
    else{
        when (movieUiState) {
            is MovieUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is MovieUiState.Success -> MoviePhotoCard(photos = movieUiState.photos, viewModel = MovieViewModel(), modifier = modifier.fillMaxSize())
            is MovieUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
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
fun MoviePhotoCard(
    photos: List<MoviePhoto>,
    viewModel: MovieViewModel,
    modifier: Modifier = Modifier) {
   Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       val photo1 = viewModel.photo1
       val photo2 = viewModel.photo2
       val score = viewModel.score
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
                .padding(bottom = 10.dp)
                .padding(top = 20.dp)
                .size(300.dp)
                .height(300.dp)
        )
       Box(Modifier.padding(bottom = 10.dp)) {
           Text(
               text = "Rating prvog filma je ${photo1.rating}"
           )
       }
       Text(
           text = "Skor je ${score}"
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
                .padding(top = 10.dp)
                .size(300.dp)
                .height(300.dp)
        )
    }
}


@Composable
fun MoviePhotoCardRow(
    photos: List<MoviePhoto>,
    viewModel: MovieViewModel,
    modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val photo1 = viewModel.photo1
        val photo2 = viewModel.photo2
        val score = viewModel.score
        Column (verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
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
                        .width(400.dp)
                        .height(500.dp)
                        .padding(end = 50.dp)
                )
                Text(
                    text = "Rating prvog filma je ${photo1.rating}"
                )
        }
        Text(
            text = "Skor je ${score}"
        )

            Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
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
                        .width(400.dp)
                        .height(500.dp)
                        .padding(start = 50.dp)
                )
                Text(
                    text = "Rating prvog filma je ??"
                )
            }
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
