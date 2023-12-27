package com.example.mvvm_room.Listado.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room


//https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room?hl=es-419#6
@Database(entities = [UsuarioEntity::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao():UserDao

    companion object {
//        @Volatile
//        private var Instance: UserDataBase? = null

//        fun getDatabase(context: Context): Any {
//            return Instance ?: synchronized(this) {
//                Room.databaseBuilder(context, UserDataBase::class.java, "usuario_database")
//                    .build()
//                    .also { Instance = it }
//            }
//        }

    }

}