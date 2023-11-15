package com.example.alertdialogs

data class itemInfo(val email:String, val draw:Int, val selected:Boolean=false, val onCheckedChange:(Boolean)->Unit)
