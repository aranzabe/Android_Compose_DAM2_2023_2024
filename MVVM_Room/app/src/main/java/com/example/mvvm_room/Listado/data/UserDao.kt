package com.example.mvvm_room.Listado.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/*
Objeto de acceso a datos: DAO.
La funcionalidad del DAO es ocultar todas las complejidades relacionadas con la realización de
operaciones de la base de datos en la capa de persistencia, aparte del resto de la aplicación.
Esto te permite cambiar la capa de datos independientemente del código que usa los datos.
La biblioteca de Room proporciona anotaciones de conveniencia, como @Insert, @Delete y @Update,
para definir métodos que realizan inserciones, actualizaciones y eliminaciones simples sin
necesidad de escribir una instrucción de SQL.
https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room?hl=es-419#5
 */
@Dao
interface UserDao {

    @Query(value = "SELECT * from UsuarioEntity")
    fun getUsers() : Flow<ArrayList<UsuarioEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(item : UsuarioEntity)

    @Query("SELECT * from UsuarioEntity WHERE id = :id")
    fun getUser(id: Int): Flow<UsuarioEntity>

}