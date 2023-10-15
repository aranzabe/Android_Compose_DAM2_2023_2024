package com.example.botones

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.botones.ui.theme.BotonesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BotonesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EjemploBoton()
                }
            }
        }
    }
}

@Preview
@Composable
fun EjemploBoton(){
    //var disponible = true //Si no ponemos estados al volver a componer la función vuelven los estados iniciales.
    var disponible by remember { mutableStateOf(true) }
    val context = LocalContext.current
    Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Button(onClick = {
                            Log.e("Fernando","Pulsado el botón")
                            Toast.makeText(context,"Pulsado el botón",Toast.LENGTH_SHORT).show()
                            disponible = false
                         },
               colors = ButtonDefaults.buttonColors(contentColor = Color.Red, containerColor = Color.Cyan),
               border = BorderStroke(4.dp, Color.Blue),
               enabled = disponible
            ) {
            Text("Púlsame")
        }
        OutlinedButton(
            onClick = {
                Toast.makeText(context,"Pulsado el botón outlined",Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(top = 8.dp)
        )
        {
            Text("Otro")
        }
        //Este botrón no tendrá borde.
        TextButton(onClick = { Toast.makeText(context,"Pulsado el TextButton",Toast.LENGTH_SHORT).show()}) {
            Text("Esto es TextButton")
        }
    }
}
