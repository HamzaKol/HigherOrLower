package com.example.higherorlower.ui.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.higherorlower.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize().padding(36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.icon_192),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Black),
            modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
            ) {
            Text(text = "Play", fontSize = 16.sp)
        }

       OutlinedButton(
           onClick = { /*TODO*/ },
           modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
       ) {
            Text(text = "About", fontSize = 16.sp)
       }

        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
            ) {
            Text(text = "Feedback", fontSize = 16.sp)
        }
    }
}