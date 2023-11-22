package com.example.navegacion

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
                        composable(Rutas.Pantalla3+"/{numero}", arguments = listOf(navArgument("numero"){type = NavType.IntType})){argumentos ->
                            Pant3(navController, argumentos.arguments?.getInt("numero")!!)
                        }
                    }
                }
            }
        }
    }
}
/*
Por defecto los argumentos son String (ver paso de Pantalla1 a Pantalla2).
Se recomienda no pasar objetos de datos complejos cuando navegas, sino pasar la información mínima necesaria,
como un identificador único o alguna otra forma de ID, cuando se realizan acciones de navegación.
Los objetos complejos se deben almacenar como datos en una sola fuente de información, como la capa de datos.
Una vez que llegues a tu destino después de navegar, podrás cargar la información requerida desde la única fuente
de confianza mediante el ID pasado
 */