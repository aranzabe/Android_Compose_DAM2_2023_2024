package com.example.progressbar

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.progressbar.ui.theme.ProgressBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgressBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        MiProgressBar()
                        Spacer(modifier = Modifier.height(10.dp))
                        MiProgressBarAvanzado()
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MiProgressBar() {
    var showPbVal by rememberSaveable { mutableStateOf(false) }
    Column {
        if (showPbVal) {
            CircularProgressIndicator(color = Color.Blue, strokeWidth = 3.dp)
            LinearProgressIndicator(color = Color.Cyan, trackColor = Color.Green)
        }
        Button(onClick = { showPbVal = !showPbVal}) {
            if (showPbVal){
                Text("Deshabilitar")
            }
            else {
                Text("Habilitar")
            }
        }
    }
}

/**
 * Los progressBar  representan valores entre 0 y 1 (porcentajes). Pero el control de máx y mín
 * tenemos que hacerlo por código, si queremos.
 */
@Composable
fun MiProgressBarAvanzado() {
    var valor by rememberSaveable { mutableStateOf(0f) }
    Column {
        CircularProgressIndicator(color = Color.Blue, strokeWidth = 3.dp, progress = valor)
        LinearProgressIndicator(color = Color.Cyan, trackColor = Color.Green, progress = valor)
        Row() {
            Button(onClick = { valor += 0.1f }) {
                Text("Incrementar")
            }
            Button(onClick = { valor -= 0.1f }) {
                Text("Decrementar")
            }
        }
    }
}