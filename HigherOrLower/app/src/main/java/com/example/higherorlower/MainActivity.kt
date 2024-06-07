package com.example.higherorlower

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.higherorlower.ui.MainApp
import com.example.higherorlower.ui.theme.HigherOrLowerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HigherOrLowerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainApp()
                }
            }
        }
    }
}
