package com.example.app_mvvm_aws

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import com.example.app_mvvm_aws.Imagenes.ImagenesViewModel
import com.example.app_mvvm_aws.Imagenes.PantallaImagenes
import com.example.app_mvvm_aws.Listado.Listado
import com.example.app_mvvm_aws.Listado.ListadoViewModel
import com.example.app_mvvm_aws.Login.Login
import com.example.app_mvvm_aws.Login.LoginViewModel
import com.example.app_mvvm_aws.Principal.Principal
import com.example.app_mvvm_aws.Principal.PrincipalViewModel
import com.example.app_mvvm_aws.ui.theme.App_MVVM_AWSTheme

class MainActivity : ComponentActivity() {
    val pvm = PrincipalViewModel()
    val lvm = ListadoViewModel()
    val logvm = LoginViewModel()
    val imagvm = ImagenesViewModel()
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
                        Amplify.addPlugin(AWSCognitoAuthPlugin())
                        Amplify.addPlugin(AWSDataStorePlugin())
                        Amplify.addPlugin(AWSS3StoragePlugin())
                        Amplify.configure(applicationContext)
                        Log.i("Fernando", "Initialized Amplify")
                    } catch (e: AmplifyException) {
                        Log.e("Fernando", "Could not initialize Amplify", e)
                    }


                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Rutas.Principal){
                        composable(Rutas.Principal){
                            Principal(navController, pvm, lvm, imagvm)
                        }
                        composable(Rutas.Listado){
                            Listado(navController, pvm, lvm)
                        }
                        composable(Rutas.Login){
                            Login(navController, pvm, lvm, logvm)
                        }
                        composable(Rutas.Imagenes){
                            PantallaImagenes(navController, pvm, lvm, logvm, imagvm)
                        }
                    }
                }
            }
        }
    }

}

