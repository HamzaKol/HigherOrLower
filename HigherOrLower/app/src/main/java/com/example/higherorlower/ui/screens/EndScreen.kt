package com.example.higherorlower.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun EndScreen (onPlayAgain : () -> Unit, onExit: () -> Unit) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        Text(text = "Game Over!", modifier = Modifier.padding(16.dp))

        Button(onClick = onPlayAgain, modifier = Modifier.padding(16.dp)) {
            Text(text = "Play again!")
        }
        Button(onClick = onExit, modifier = Modifier.padding(16.dp)) {
            Text(text = "Exit!")
        }




    }

}