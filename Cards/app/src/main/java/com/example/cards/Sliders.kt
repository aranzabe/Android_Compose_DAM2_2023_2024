package com.example.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.min
import kotlin.math.roundToInt

@Composable
fun SliderBasico(){
    var sliderPos by remember { mutableStateOf(0f) }
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Slider(value = sliderPos, onValueChange = {
            sliderPos = it
        })
        Text(text = sliderPos.toString())
    }
}

@Composable
fun SliderAvanzado(){
    var sliderPos by remember { mutableStateOf(0f) }
    var valorPuntual by remember { mutableStateOf("") }
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Slider(
            value = sliderPos,
            valueRange = 0f..10f,
            steps = 9,
            onValueChange = {
                 sliderPos = it
            },
            onValueChangeFinished = { //Este evento se dispara cuando estamos en las posiciones de los step.
                valorPuntual = sliderPos.roundToInt().toString()
            }
            )
//        Text(text = sliderPos.toString())
        Text(text = valorPuntual)
    }
}

//Para investigar el rangeSlider.