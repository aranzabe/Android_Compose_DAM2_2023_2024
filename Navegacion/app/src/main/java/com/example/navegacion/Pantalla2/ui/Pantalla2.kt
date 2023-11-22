package com.example.navegacion.Pantalla2.ui

import Modelo.Usuario
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
            //Sin argumentos
            navController.navigate(Rutas.Pantalla3)
            //Con argumento objeto.
//            var u = Usuario("dam2@gmail.com",120)
//            navController.navigate(Rutas.Pantalla3+"/$u")
        }) {
            Text(text = "Pantalla 3")
        }
    }
}

