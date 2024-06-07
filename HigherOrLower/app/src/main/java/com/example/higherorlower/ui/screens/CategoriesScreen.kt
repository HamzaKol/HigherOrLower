package com.example.higherorlower.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.higherorlower.R
import com.example.higherorlower.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(title: String, image: Int, onClickEvent: () -> Unit) {
    Card (
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        onClick = onClickEvent

    ) {
        
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = image), contentDescription = null, modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            Text(text = title, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
            )
        }

    }
}


@Composable
fun CategoriesScreen (onClickMovie: () -> Unit) {
    Column {
        CategoryCard(title = "Imdb: rating", image = R.drawable.imdb, onClickMovie)
        CategoryCard(title = "Spotify: most popular artists", image = R.drawable.spotify, onClickMovie)


    }
}