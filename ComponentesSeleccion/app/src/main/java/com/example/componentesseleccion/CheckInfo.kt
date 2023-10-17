package com.example.componentesseleccion

data class CheckInfo (val title:String, val selected:Boolean=false, val onCheckedChange:(Boolean)->Unit)