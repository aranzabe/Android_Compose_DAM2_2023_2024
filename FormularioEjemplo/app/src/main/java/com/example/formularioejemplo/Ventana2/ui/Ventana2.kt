package com.example.formularioejemplo.Ventana2.ui

import Modelo.Almacen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.formularioejemplo.Rutas

@Composable
fun Pant2(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        ){
        Text(text = "Hola esto es la pantalla 2")
        Text(text = "Valor del arrayList: ${Almacen.usuarios.toString()}")
        Button(onClick = {
            navController.navigate(Rutas.Login)
        }) {
            Text(text = "Volver")
        }
    }
}