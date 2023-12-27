package com.example.app_mvvm_aws

import android.os.Bundle
import android.util.Log
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
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.example.app_mvvm_aws.Listado.Listado
import com.example.app_mvvm_aws.Listado.ListadoViewModel
import com.example.app_mvvm_aws.Principal.Login
import com.example.app_mvvm_aws.Principal.PrincipalViewModel
import com.example.app_mvvm_aws.Rutas.Listado
import com.example.app_mvvm_aws.ui.theme.App_MVVM_AWSTheme

class MainActivity : ComponentActivity() {
    val pvm = PrincipalViewModel()
    val lvm = ListadoViewModel()
//https://sandbox.amplifyapp.com/getting-started/0a3cf0e0-d5d9-4f89-ad5d-45b6888234ad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_MVVM_AWSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    try {
                        Amplify.addPlugin(AWSApiPlugin()) // UNCOMMENT this line once backend is deployed
                        Amplify.addPlugin(AWSDataStorePlugin())
                        Amplify.configure(applicationContext)
                        Log.i("Fernando", "Initialized Amplify")
                    } catch (e: AmplifyException) {
                        Log.e("Fernando", "Could not initialize Amplify", e)
                    }


                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Rutas.Principal){
                        composable(Rutas.Principal){
                            Login(navController, pvm, lvm)
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

