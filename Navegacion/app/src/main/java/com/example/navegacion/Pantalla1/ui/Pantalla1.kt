package com.example.navegacion.Pantalla1.ui

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
fun Pant1(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)){
        Text(text = "Pantalla 1")
        Button(onClick = {  navController.navigate(Rutas.Pantalla2+"/DAM2")  }) {
            Text(text = "Pantalla 2")
        }
    }
}
