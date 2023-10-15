package com.example.estados

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.estados.ui.theme.EstadosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EstadosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    misEstados()
                }
            }
        }
    }
}



@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun misEstados(){
    //Al poner el cont con by en lugar de con el igual nos permite acceder al valor sin usar el .value.
    var cont by remember{ mutableStateOf(0)} //Mantiene el estado cuando esta función @Conmposable se recrea. Si oriento la ventana como la vista se detruye, no vladŕia.
    //var cont = rememberSaveable { mutableStateOf(0)} //Mantendrá este estado aunque me cargue la ventana.
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            cont++
        }) {
            Text(text = "Pulsar")
        }
        Text(text = "He sido pulsado ${cont} veces")
        //Text(text = "He sido pulsado ${cont.value} veces")
    }
}