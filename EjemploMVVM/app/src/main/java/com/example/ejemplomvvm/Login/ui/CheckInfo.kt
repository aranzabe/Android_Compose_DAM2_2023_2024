package com.example.formularioejemplo.login.ui

data class CheckInfo (val title:String, val selected:Boolean=false, var enable:Boolean=true, val onCheckedChange:(Boolean)->Unit)