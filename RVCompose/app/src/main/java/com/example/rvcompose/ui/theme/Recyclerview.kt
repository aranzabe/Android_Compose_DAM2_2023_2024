package com.example.rvcompose.ui.theme

import android.text.Layout.Alignment
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rvcompose.Modelo.Usuario
import com.example.rvcompose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun simpleRecyclerView(){
    val miLista = listOf<String>("David","Manuel","Eduardo","Marco")
    LazyColumn{
        item {
            Text(text = "Item 1")
        }
        items(4){
            Text(text = "Item $it")
        }
        items(miLista){
            Text(text = "Soy $it")
        }
    }
}

/**
 * Cabeceras del RV. Permite agrupar los items por el valor de un campo.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RVUsuariosSticky(){
    val context = LocalContext.current
    val usuariosMostrar = generarUsuarios().groupBy { it.nombre }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {

        usuariosMostrar.forEach{nombre, miUsuario ->
            stickyHeader {
                Text(text =nombre, modifier = Modifier.fillMaxWidth().background(color = Color.LightGray), fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
            items(miUsuario){
                ItemUsuarioLista(u = it){usu, tipo -> //Llamada a la función lamda clickable del card en ItemUsuario.
                    if (tipo == 1) {//Click
                        Log.e("Fernando","Click pulsado")
                        Toast.makeText(context, "Usuario seleccionado: $usu", Toast.LENGTH_SHORT).show()
                    }
                    if (tipo == 2){//Long click
                        Log.e("Fernando","Long click pulsado")
                    }
                    if (tipo == 3){//Long click
                        Log.e("Fernando","Double click pulsado")
                    }
                }
            }
        }
    }
}

/**
 * Hay una serie de funciones útiles que me indican en qué lugar del Scroll estoy.
 */
@Composable
fun RVUsuariosControlsView(){
    val context = LocalContext.current
    val rvEstado = rememberLazyListState()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(generarUsuarios()){
            ItemUsuarioLista(u = it){usu, tipo -> //Llamada a la función lamda clickable del card en ItemUsuario.
                if (tipo == 1) {//Click
                    Log.e("Fernando","Click pulsado")
                    Toast.makeText(context, "Usuario seleccionado: $usu", Toast.LENGTH_SHORT).show()
                }
                if (tipo == 2){//Long click
                    Log.e("Fernando","Long click pulsado")
                }
                if (tipo == 3){//Long click
                    Log.e("Fernando","Double click pulsado")
                }
            }
        }
    }
    Log.e("Fernando",rvEstado.firstVisibleItemIndex.toString())
    Log.e("Fernando",rvEstado.firstVisibleItemScrollOffset.toString())
}

/**
 * Para RV clásicas: LazyRow o LazyColumn nos orientará la RV en el sentido indicado.
 */
@Composable
fun RVUsuariosCols(){
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(generarUsuarios()){
            ItemUsuarioLista(u = it){usu, tipo -> //Llamada a la función lamda clickable del card en ItemUsuario.
                if (tipo == 1) {//Click
                    Log.e("Fernando","Click pulsado")
                    Toast.makeText(context, "Usuario sel: $usu", Toast.LENGTH_SHORT).show()
                }
                if (tipo == 2){//Long click
                    Log.e("Fernando","Long click pulsado")
                }
                if (tipo == 3){//Long click
                    Log.e("Fernando","Double click pulsado")
                }
            }
        }
    }
}

/**
 * Para RV clásicas: LazyRow o LazyColumn nos orientará la RV en el sentido indicado.
 */
@Composable
fun RVUsuariosFilas(){
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items(generarUsuarios()){
            ItemUsuario(u = it){//Llamada a la función lamda clickable del card en ItemUsuario.
                Toast.makeText(context,"Usuario seleccionado: $it",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

/**
 * Para hacer un Grid: LazyVerticalGrid y LazyHorizontalGrid.
 */
@Composable
fun GridUsuarios(){
    val context = LocalContext.current
    LazyVerticalGrid(verticalArrangement = Arrangement.Center,
        columns = GridCells.Fixed(2),
        content =  {
        items(generarUsuarios()){
            ItemUsuario(u = it){//Llamada a la función lamda clickable del card en ItemUsuario.
                Toast.makeText(context,"Usuario seleccionado: $it",Toast.LENGTH_SHORT).show()
            }
        }
    },
        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp)
        //Esto es el padding de todo el Grid. Si queremos separar cada item hay que tocar el padding de cada ItemUsuario en la función de abajo "ItemUsuario".
        )
}

/**
 * Para mostrar la RV en columnas o en Grid.
 * En este ejemplo, el Modifier.pointerInput se utiliza para detectar gestos táctiles y el evento
 * detectTransformGestures para calcular la distancia del gesto. Si la distancia del gesto supera
 * un umbral, se considera como un "long click". El comportamiento real del "long click" se puede
 * personalizar según tus necesidades. En este caso, simplemente imprime un mensaje en el registro.
 */
@Composable
fun ItemUsuario(u : Usuario, onItemSeleccionado:(Usuario)->Unit){
    var isLongClick by remember { mutableStateOf(false) }
    var context = LocalContext.current

    Card(border = BorderStroke(2.dp, Color.Blue),
        modifier = Modifier
            //.fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        Log.e("Fernando","Press pulsado")
                        onItemSeleccionado(u)
                    },
                    onTap = {
                        Log.e("Fernando","Tap pulsado")
                    },
                    onLongPress = {
                        Log.e("Fernando","LongPress pulsado")
                        isLongClick = true
                    },
                    onDoubleTap = {
                        Log.e("Fernando","DoubleTap pulsado")
                    }
                )
            } //Para que funcione pointerInput, debe estar comentado 'clickable'
//            .clickable {
//                onItemSeleccionado(u) //Función lamda llamada desde RVUsuarios.
//            }
            .padding(top = 1.dp, bottom = 1.dp, start = 1.dp, end = 1.dp)
    )
    {
        Image(painter = painterResource(id = u.imagen), contentDescription = "Avatar",
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp)
        )
        Text(text = u.nombre, modifier = Modifier
            .align(CenterHorizontally)
            .padding(2.dp))
        Text(
            text = u.edad.toString(),
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(2.dp),
            fontSize = 12.sp
        )
        Button(onClick = {onItemSeleccionado(u) }) {
            Text(text = "Seleccionar")
        }
    }
}

/**
 * Para mostrar en lista de RV. En este ejemplo, también, la función a la que se llama
 * se le envía más de un parámetro, para que sepamos como hacerlo.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemUsuarioLista(u : Usuario, onItemSeleccionado:(Usuario, Int)->Unit){
    var isLongClick by remember { mutableStateOf(false) }
    var isClick by remember { mutableStateOf(false) }
    var isDoubleClick by remember { mutableStateOf(false) }
    var context = LocalContext.current

    Card(border = BorderStroke(2.dp, Color.Blue),
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onDoubleClick = {
                    Log.e("Fernando","Doble click pulsado")
                    isDoubleClick = true
                    onItemSeleccionado(u,3)
                },
                onLongClick = {
                    Log.e("Fernando","LongPress pulsado")
                    isLongClick = true
                    onItemSeleccionado(u,2)
                },
                 onClick = {
                     Log.e("Fernando","Click pulsado")
                     isClick = true
                     onItemSeleccionado(u,1)
                 }
            )//Para que funcione lo anterior se debe comentar lo de 'clickable'
//            .clickable {
//                    onItemSeleccionado(u,1) //Función lamda llamada desde RVUsuarios.
//            }
            .padding(top = 1.dp, bottom = 1.dp, start = 1.dp, end = 1.dp)
    )
    {
        Image(painter = painterResource(id = u.imagen), contentDescription = "Avatar",
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp)
        )
        Text(text = u.nombre, modifier = Modifier
            .align(CenterHorizontally)
            .padding(2.dp)
            .combinedClickable(
                onDoubleClick = {
                    Log.e("Fernando","Doble click pulsado")
                },
                onLongClick = {
                    Log.e("Fernando","LongPress pulsado")
                    isLongClick = true
                },
                onClick = {
                    Log.e("Fernando","Click pulsado")
                }
            ))
        Text(
            text = u.edad.toString(),
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(2.dp),
            fontSize = 12.sp
        )
        Button(onClick = {onItemSeleccionado(u,1) }) {
            Text(text = "Seleccionar")
        }
    }
}

fun generarUsuarios():ArrayList<Usuario> {
    var lista = ArrayList<Usuario>()
    val nombres = listOf("David","Manuel","Eduardo","Marco","Óscar","Airón","Antonio","Adrián","Raúl", "Sergio","Ramón","Álvaro","Alfonso","Lorenzo","María","José")
    val imagenes = listOf(R.drawable.icono1, R.drawable.icono2, R.drawable.icono3, R.drawable.icono4)
    for (i in 1..10){
        lista.add(Usuario(nombres.get(Random.nextInt(0,nombres.size)),Random.nextInt(18,100),imagenes.get(Random.nextInt(0,imagenes.size))))
    }
    return lista
}