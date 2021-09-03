package com.github.brunoomoreshi.dejitarumeishidesu.data
//Objeto de acesso de dados no ROOM

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MeishiDAO {
    //Implementa a busca
    @Query("SELECT * FROM Meishi")
    fun getAll(): LiveData<List<Meishi>>

    //Implementa o add.
    //Tá com .IGNORE, mas sei lá... podia ser .REPLACE, assim ficamos com a mais recente...
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(meishi: Meishi)
}