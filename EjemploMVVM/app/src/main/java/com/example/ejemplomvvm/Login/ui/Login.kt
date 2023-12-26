package com.example.formularioejemplo.login.ui

import Modelo.Almacen
import Modelo.Usuario
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemplomvvm.Login.ui.ViewModel.LoginViewModel
import com.example.ejemplomvvm.R
import com.example.ejemplomvvm.Rutas

@Composable
fun Login(navController: NavHostController, loginViewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center), navController, loginViewModel)
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}


@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun Body(modifier: Modifier, navController: NavHostController, loginViewModel: LoginViewModel) {
    val email:String by loginViewModel.email.observeAsState(initial = "")
    val password:String by loginViewModel.password.observeAsState(initial = "")
    val estadoCheck:Boolean by loginViewModel.estadoCheck.observeAsState(initial = false)
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)
    var context = LocalContext.current
    var checkInfo = CheckInfo(
        title="Opción 1",
        selected = estadoCheck,
        onCheckedChange = {loginViewModel.estadoCheck.value}
    )


    Column(modifier = modifier) {
        ImageLogo(
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp))
        Spacer(modifier = Modifier.size(12.dp))
        Email(email) {
            //loginViewModel.onLoginCambiado(it)
            loginViewModel.onRegistroCambiado(email = it, pass = password)
//            isLoginEnable = email.isNotEmpty() && password.isNotEmpty()
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            loginViewModel.onRegistroCambiado(email = email, pass = it)
            //loginViewModel.onPassCambiado(it)
  //          isLoginEnable = email.isNotEmpty() && password.isNotEmpty()
        }
        Spacer(modifier = Modifier.size(16.dp))
        MiCheckBoxObjeto(checkInfo){
            loginViewModel.onCheckCambiado(it)
        }
        Spacer(modifier = Modifier.size(4.dp))
        LoginButton(isLoginEnable){
            var us = Usuario(email, password, estadoCheck)
            loginViewModel.addUsuario(us)
            Log.e("Fernando",loginViewModel.usuarios.toString())
            Toast.makeText(context, "Usuario almacenado", Toast.LENGTH_SHORT).show()
            navController.navigate(Rutas.Pantalla2)
        }
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        //CerrarSesion(Modifier.fillMaxWidth())
        CerrarSesion()
    }
}



@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onClickAction: (Boolean) -> Unit) {
    Button(
        onClick = {
            onClickAction(true);
        },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Iniciar sesión")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {
    var showPassword by remember { mutableStateOf(value = false) }
    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        label = {Text("Introduce una clave")},
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFFAFAFA)),
        placeholder = { Text("Password") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(
                    onClick = { showPassword = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "hide_password"
                    )
                }
            }
        },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFFAFAFA)),
        placeholder = { Text(text = "Email") },
        label = {Text("Introduce un correo")},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun MiCheckBoxObjeto(info: CheckInfo, onCheckChanged: (Boolean) -> Unit) {
    Row() {
        Checkbox(
            checked = info.selected,
            enabled = info.enable,
            onCheckedChange = {
                info.onCheckedChange(!info.selected)
                onCheckChanged(it)
            })
        Text(info.title, modifier = Modifier.padding(vertical = 12.dp))
    }
}


@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "logo",
        modifier = modifier
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "close app",
        modifier = modifier.clickable { activity.finish() })
}
@Composable
//fun CerrarSesion(mod:Modifier){
fun CerrarSesion(){
    val activity = LocalContext.current as Activity
    Button(modifier = Modifier.fillMaxWidth(), onClick = { activity.finish() }) {
        Text(text = "Cerrar aplicación")
    }
}