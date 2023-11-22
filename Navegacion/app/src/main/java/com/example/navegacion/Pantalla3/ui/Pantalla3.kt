package com.example.navegacion.Pantalla3.ui

import Modelo.Usuario
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.navegacion.ui.theme.Rutas

@Composable
fun Pant3(navController: NavHostController, usuari: Usuario) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)){
        Text(text = "hola usuario ${usuari} esto es la pantalla 3")
        Button(onClick = {  navController.navigate(Rutas.Pantalla1)  }) {
            Text(text = "Pantalla 1")
        }
    }
}

@Composable
fun Pant3(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)){
        Text(text = "Esto es la pantalla 3")
        Button(onClick = {  navController.navigate(Rutas.Pantalla1)  }) {
            Text(text = "Pantalla 1")
        }
    }
}