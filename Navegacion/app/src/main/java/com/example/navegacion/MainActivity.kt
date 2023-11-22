package com.example.navegacion

import Modelo.Usuario
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navegacion.Pantalla1.ui.Pant1
import com.example.navegacion.Pantalla2.ui.Pant2
import com.example.navegacion.Pantalla3.ui.Pant3
import com.example.navegacion.ui.theme.NavegacionTheme
import com.example.navegacion.ui.theme.Rutas

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegacionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Pant1"){
                        composable(Rutas.Pantalla1){ Pant1(navController) }
                        composable(Rutas.Pantalla2+"/{nombre}"){argumentos ->
                            Pant2(navController, argumentos.arguments?.getString("nombre")!!) }
                        composable(Rutas.Pantalla3){ Pant3(navController) }
//                        composable(Rutas.Pantalla3+"/{usu}", arguments = listOf(navArgument("usu"){ type = NavType.ParcelableType(Usuario::class.java)})){argumentos ->
//                            Pant3(navController, argumentos.arguments?.getParcelable("usu")!!) }
                    }
                }
            }
        }
    }
}
/*
Por defecto los argumentos son String (ver paso de Pantalla1 a Pantalla2.
Si queremos especificar otros tipos de argumentos ver paso de Pantalla2 a Pantalla3.
 */