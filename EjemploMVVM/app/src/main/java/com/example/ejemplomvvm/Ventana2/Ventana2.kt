package com.example.formularioejemplo.Ventana2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.ejemplomvvm.Login.ui.ViewModel.LoginViewModel
import com.example.ejemplomvvm.Rutas

@Composable
fun Pant2(navController: NavHostController, loginViewModel: LoginViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        ){
        Text(text = "Hola esto es la pantalla 2")
        Text(text = "Valor del arrayList: ${loginViewModel.usuarios.toString()}")
        Button(onClick = {
            navController.navigate(Rutas.Login)
        }) {
            Text(text = "Volver")
        }
    }
}