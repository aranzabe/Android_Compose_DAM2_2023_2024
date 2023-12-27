package com.example.app_mvvm_aws.Principal

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.app_mvvm_aws.Listado.ListadoViewModel
import com.example.app_mvvm_aws.Rutas
import com.amplifyframework.datastore.generated.model.Usuario

@Composable
fun Login(
    navController: NavHostController,
    principalViewModel: PrincipalViewModel,
    listadoViewModel: ListadoViewModel
) {
    val showDialog: Boolean by principalViewModel.showDialog.observeAsState(false)


    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column {
            Text("Ventana principal", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            //Body( navController, principalViewModel, listadoViewModel)
            Column (modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                AddUserDialog(
                    principalViewModel,
                    showDialog,
                    onDismiss = { principalViewModel.onDialogClose() },
                    onUserAdded = {
                        principalViewModel.onDialogClose()
                        listadoViewModel.onUserCreated(it)
                    })
            }
            IrListadoButton(){
                listadoViewModel.getUsers()
                navController.navigate(Rutas.Listado)
            }
            CerrarSesion()
            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                    horizontalAlignment = Alignment.End)
                    {
                        FloatingActionButtonabDialog(principalViewModel)
                    }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserDialog(principalViewModel:PrincipalViewModel, show: Boolean, onDismiss: () -> Unit, onUserAdded: (Usuario?) -> Unit) {
    val nombre: String by principalViewModel.nombre.observeAsState("")
    val edad:Int by principalViewModel.edad.observeAsState(0)
    val parte:Boolean by principalViewModel.parte.observeAsState(false)

    //En lugar de Dialog se puede usar Popup.
    if (show) {
        Dialog(onDismissRequest = { onDismiss() },

            //properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false) ) {
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false) ) {
            Column(
            modifier = Modifier
//                .height(450.dp)
                .background(Color.White)
                .padding(20.dp)
                ) {
                TituloDialogo(texto = "Rellena los datos")
                Spacer(modifier = Modifier.size(10.dp))
                Text("Nombre: ")
                TextField(
                    value = nombre,
                    onValueChange = { principalViewModel.cambiaNombre(it) },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text("Edad:")
                TextField(
                    value = edad.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    onValueChange = { principalViewModel.cambiaEdad(it.toInt()) },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(10.dp))
                Checkbox(
                    checked = parte,
                    onCheckedChange = { principalViewModel.cambiaParte(it) })
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = {
                    val u = Usuario.builder().nombre(nombre).edad(edad).parte(parte).build()
                    Rutas.autoId++
                    onUserAdded(u)
                }){
                    Text("Añadir")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = {
                        onDismiss()
                    }) {
                    Text("Salir")
                }
            }
        }
    }
}


@Composable
fun TituloDialogo(texto:String){
    Text(text = texto, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
}


@Composable
fun IrListadoButton(onClickAction: (Boolean) -> Unit) {
    Button(
        onClick = {
            onClickAction(true);
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Ir al listado")
    }
}

@Composable
fun CerrarSesion(){
    val activity = LocalContext.current as Activity
    Button(modifier = Modifier.fillMaxWidth(), onClick = { activity.finish() }) {
        Text(text = "Cerrar aplicación")
    }
}

@Composable
fun FloatingActionButtonabDialog(principalViewModel: PrincipalViewModel) {
    val miniFabSize = 40.dp
    FloatingActionButton(
        onClick = {
            principalViewModel.onShowDialogClick()
        },
        elevation = FloatingActionButtonDefaults.elevation(16.dp),
        modifier = Modifier
            .size(miniFabSize)
            .fillMaxWidth()
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Añadir usuario")
        }
}

