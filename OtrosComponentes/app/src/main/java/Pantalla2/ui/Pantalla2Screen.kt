package Pantalla2.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.otroscomponentes.R
import com.example.otroscomponentes.Rutas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla2(navController: NavHostController) {

    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            MiToolBar( "Pantalla 2", {
                Toast.makeText(context, "Pulsado retroceso $it", Toast.LENGTH_SHORT).show()
                navController.navigate(Rutas.Pantalla1)
            },
                {
                    val job = GlobalScope.launch(Dispatchers.Main) {
                        snackbarHostState.showSnackbar(message = "Pulsado marcador $it", actionLabel = "Close", duration = SnackbarDuration.Indefinite)
                        //Si en tiempo ponemos short se oculta en un breve tiempo.
                    }
                    //Toast.makeText(context, "Pulsado marker $it",Toast.LENGTH_SHORT).show()
                },
                {
                    Toast.makeText(context, "Pulsado share $it", Toast.LENGTH_SHORT).show()
                }
            ){
                Toast.makeText(context, "Pulsado menú puntos $it", Toast.LENGTH_SHORT).show()
            }
        },
        bottomBar = {
            MiBottonNavigation({
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            },
                {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                },
                {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }){
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        },
        floatingActionButton = {
            MiFloatingButton(){
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        },
        floatingActionButtonPosition = FabPosition.Center,  //Por defecto lo pone a la derecha.

    )//Fin scafold
    {

    }
}



@Composable
fun Body() {
    Column {
        Text(text = "Esto en el boy")
    }
}



//https://www.develou.com/bottom-app-bar/
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MiToolBar(
    title: String,
    onNavigationClick: (String) -> Unit,
    onMarkerClick : (String) -> Unit,
    onShareClick : (String) -> Unit,
    onSettingClick: (String) -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Cyan,
            titleContentColor = Color.White,
            actionIconContentColor = Color.Yellow,
            navigationIconContentColor = Color.Red,
            scrolledContainerColor = Color.Green
        ),
        modifier = Modifier.background(color = Color.Blue),
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { onNavigationClick("<--") }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Ir hacia arriba")
            }
        },
        actions = {
            IconButton(onClick = { onMarkerClick("M") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = "Leer después"
                )
            }
            IconButton(onClick = { onShareClick("S") }) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "Compartir")
            }

            IconButton(onClick = { onSettingClick("...") }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Ver más")
            }
        }
    )

}



@Composable
fun MiBottonNavigation(
    onEliminarClick: (String) -> Unit,
    onLocalizarClick : (String) -> Unit,
    onBuscarClick : (String) -> Unit,
    onFloatingButtonClick:(String) -> Unit
) {

    BottomAppBar(

        containerColor = Color(0xffbbdefb),
        actions = {
            IconButton(onClick = {
                onEliminarClick("Eliminando")
            }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Eliminando..."
                )
            }
            IconButton(onClick = { onLocalizarClick("Localizando...")
            }) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Localizar"
                )
            }
            IconButton(onClick = {
                onBuscarClick("Buscando...")
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscando..."
                )
            }
        }
//        ,
//        floatingActionButton = {
//            FloatingActionButton(onClick = { onFloatingButtonClick("Botón + pulsado...")
//            }) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Agregar"
//                )
//            }
//        }
    )
}

@Composable
fun MiFloatingButton( onFloatingButtonClick:(String) -> Unit) {
    FloatingActionButton(onClick = { onFloatingButtonClick("Botón + pulsado...")
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Agregar"
        )
    }
}