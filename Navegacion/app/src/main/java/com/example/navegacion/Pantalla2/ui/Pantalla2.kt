package com.example.navegacion.Pantalla2.ui

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.navegacion.Pantalla3.ui.Pant3
import com.example.navegacion.ui.theme.Rutas

@Composable
fun Pant2(navController: NavHostController, nombre:String) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)){
        Text(text = "Hola $nombre esto es la pantalla 2")
        Button(onClick = {
            navController.navigate(Rutas.Pantalla3+"/126")
        }) {
            Text(text = "Pantalla 3")
        }
    }
}


