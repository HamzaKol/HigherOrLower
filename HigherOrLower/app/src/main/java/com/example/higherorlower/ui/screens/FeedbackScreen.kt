package com.example.higherorlower.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeedbackScreen() {
    val context = LocalContext.current
    var subject by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Kontaktirajte nas!",
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding( top = 16.dp)

            )
        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text("Naslov") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        OutlinedTextField(
            value = body,
            onValueChange = { body = it },
            label = { Text("Poruka") },
            modifier = Modifier
                .fillMaxWidth().fillMaxHeight().weight(weight = 0.6f)
                .padding(16.dp)
        )
        Button (
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:") // Only email apps should handle this
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("amar.koric02@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, body)
                }
                // Verify that there's an email app to handle this intent
                context.startActivity(intent)

            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Send Email")
        }
    }
}

