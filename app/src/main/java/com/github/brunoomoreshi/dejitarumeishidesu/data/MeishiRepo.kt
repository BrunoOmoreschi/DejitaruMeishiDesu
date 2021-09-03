package com.github.brunoomoreshi.dejitarumeishidesu.data
//Repositório que conversa com a DAO

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MeishiRepo (private val dao: MeishiDAO){
    fun insert (meishi: Meishi) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(meishi)
        }
    }
    fun getAll() = dao.getAll()
}