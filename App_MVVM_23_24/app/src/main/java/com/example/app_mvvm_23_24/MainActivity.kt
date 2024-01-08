package com.example.app_mvvm_23_24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_mvvm_23_24.Listado.Listado
import com.example.app_mvvm_23_24.Listado.ListadoViewModel
import com.example.app_mvvm_23_24.Principal.Principal
import com.example.app_mvvm_23_24.Principal.PrincipalViewModel

import com.example.app_mvvm_23_24.ui.theme.App_MVVM_23_24Theme

class MainActivity : ComponentActivity() {
    val pvm = PrincipalViewModel()
    val lvm = ListadoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_MVVM_23_24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Rutas.Principal){
                        composable(Rutas.Principal){
                            Principal(navController, pvm, lvm)
                        }
                        composable(Rutas.Listado){
                            Listado(navController, pvm, lvm)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App_MVVM_23_24Theme {
        Greeting("Android")
    }
}