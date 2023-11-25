package com.example.otroscomponentes

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * La función lambda opcionElegida servirá para que la opción elegida del menú se pueda cargar en
 * una variable de estado en Main y al volver a repintarse el Scaffold lo haga con el body correcto.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiScaffold(navController: NavHostController, pantallaCargar:String,  opcionElegida:(String)->Unit) {

    var snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val items = listOf(Icons.Default.Info, Icons.Default.Settings, Icons.Default.Call)
    val opcs = generarOpcionesMenu()
    var selectedItem = remember { mutableStateOf(items[0]) }
    var selectedItemMiOpcion by remember { mutableStateOf(opcs[0]) }
    var scope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }

    val act = LocalContext.current as Activity


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))

                //Rellenando con iconos predefinidos y sus descripciones.
//                items.forEach { item ->
//                    NavigationDrawerItem(
//                        icon = { Icon(item, contentDescription = null) },
//                        label = { Text(item.name) },
//                        selected = item == selectedItem.value,
//                        onClick = {
//                            scope.launch {
//                                drawerState.close()
//                            }
//                            selectedItem.value = item //Aquí obtenemos el seleccionado.
//                            Toast.makeText(context,"Seleccionado ${selectedItem.value.name.toString()}",Toast.LENGTH_SHORT).show()
//                            if (selectedItem.value.name == "Filled.Call"){
//                                navController.navigate(Rutas.Pantalla2)
//                            }
//                        },
//                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                    )
//                }

                //Con opciones personalizadas

                opcs.forEach{
                    NavigationDrawerItem(
                        icon = { Icon(it.icono, contentDescription = it.opcion) },
                        label = { Text(it.opcion) },
                        selected = it.opcion == selectedItemMiOpcion.opcion,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            selectedItemMiOpcion = it //Aquí obtenemos el seleccionado.
//                            OpcionElegida.eleccion = it.opcion
                            Toast.makeText(context,"Seleccionado ${selectedItemMiOpcion.opcion}",Toast.LENGTH_SHORT).show()
                            if (selectedItemMiOpcion.opcion == "Ir a ventana 2"){
                                navController.navigate(Rutas.Pantalla2)
                            }
                            if (selectedItemMiOpcion.opcion == "Salir"){
                                act.finish()
                            }
                            opcionElegida(it.opcion)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

            }
        },
        content = {

            //Para que funcione el menú hamburguesa el Scaffold debe estar dentro de un ModelDrawerSheet desde material 3.
            Scaffold(
                topBar = {
                    MiToolBar("Pantalla principal", drawerState, expanded, {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    },
                        {
                            //val job = GlobalScope.launch(Dispatchers.Main) {
                            scope.launch {
                                snackbarHostState.showSnackbar(message = "Pulsado marcador $it", actionLabel = "Close", duration = SnackbarDuration.Indefinite)
                                //Si en tiempo ponemos short se oculta en un breve tiempo.
                            }
                            //Si en tiempo ponemos short se oculta en un breve tiempo.
                            Toast.makeText(context, "Pulsado marker $it",Toast.LENGTH_SHORT).show()
                        },
                        {
                            Toast.makeText(context, "Pulsado share $it", Toast.LENGTH_SHORT).show()
                            navController.navigate(Rutas.Pantalla2)
                        }
                    ) {
                        Toast.makeText(context, "Pulsado menú puntos $it", Toast.LENGTH_SHORT)
                            .show()
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
                        }) {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                },
                floatingActionButton = {
                    MiFloatingButton() {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                },
                floatingActionButtonPosition = FabPosition.End,  //Por defecto lo pone a la derecha.
            )//Fin scafold
            //Cuerpo del Scafold (el Body).
            {padding -> //Aquí iría el body. El padding es el margen que automáticamente hay que añadir para superar la toolbar.
                //Cuando se usaba el menú hamburguesa aquí iban fragmentos que se iban machacando unos a otros.
                //Con Compose haremos visibles unos layouts u otros.
                if (pantallaCargar == "Principal"){
                    Column(modifier = Modifier.padding(padding)) {
                        Text(text = "Esto va al body, soy la pantalla principal.")
                    }
                }
                Log.e("Fernando","Aquí")
                //Lo que viene a continuación serían como los fragments. Se puede hacer lo mismo si elojo de las opciones de mení de puntos.
                if (pantallaCargar == "Opción 1"){
                    Column(modifier = Modifier.padding(padding)){
                        Text(text = "Opción 1 seleccionada del menú.")
                    }
                }
                if (pantallaCargar== "Opción 2"){
                    Column(modifier = Modifier.padding(padding)){
                        Text(text = "Opción 2 seleccionada del menú.")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiDropDown(isExpanded: Boolean, setExpanded: (Boolean) -> Unit, setSelected:(String)->Unit) {
//    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var usuarios = listOf<String>("Álvaro", "José", "Lorenzo", "Ramón", "Sergio", "María")

    Column(modifier = Modifier.padding(10.dp)) {
        DropdownMenu(expanded = isExpanded, onDismissRequest = {
            setExpanded(false)
        })
        {
            usuarios.forEach {
                DropdownMenuItem(text = { Text(text = it) }, onClick = {
                    setExpanded (false)
                    setSelected(it)
                })
            }
        }
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
    drawerState : DrawerState,
    expanded : Boolean,
    onNavigationClick: (String) -> Unit,
    onMarkerClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onSettingClick: (String) -> Unit
) {
    var scope = rememberCoroutineScope()
    val context = LocalContext.current
    var exp by remember { mutableStateOf(expanded) }

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
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
                onNavigationClick("Menu")
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menú desplegable")
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

            IconButton(onClick = {
                exp = true
                onSettingClick("...")
            }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Ver más")
            }

            MiDropDown(exp, {
                exp = it
            }){
                Toast.makeText(context, "Seleccionado $it",Toast.LENGTH_SHORT).show()
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

@Composable
fun MenuHamburguesa(){
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Opción 1", modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp))
        Text(text = "Opción 2", modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp))

        Text(text = "Ir ventana 2", modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp))
        
    }
}


fun generarOpcionesMenu() : ArrayList<OpcionMenu> {
    var titulos = listOf("Opción 1", "Opción 2", "Ir a ventana 2", "Salir")
    var iconos =  listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.ArrowForward, Icons.Default.ExitToApp)
    var opciones= ArrayList<OpcionMenu>()
    for(i in 0..titulos.size-1){
        opciones.add(OpcionMenu(titulos.get(i), iconos.get(i)))
    }
    return opciones
}

data class OpcionMenu(val opcion: String, val icono: ImageVector)

//Esto es un poco ñapa, lo mejoraremos cunado veamos ViewModel.
//object OpcionElegida {
//    var eleccion = ""
//}