package com.example.mvvm_room.Listado.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuarioEntity")
data class UsuarioEntity (@PrimaryKey(autoGenerate = true) var id:Int, @ColumnInfo("nombre") var nombre:String,@ColumnInfo("edad") var edad:Int,@ColumnInfo("parte") var parte:Boolean)