package com.github.brunoomoreshi.dejitarumeishidesu.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.brunoomoreshi.dejitarumeishidesu.data.Meishi
import com.github.brunoomoreshi.dejitarumeishidesu.data.MeishiRepo

//Estrutura que chama os cartões de visita.
class MainViewModel (private val meishiRepo: MeishiRepo) :  ViewModel(){
    //Função que vai inserir um cartão de vistas novo no repositório.
    fun insert (meishi: Meishi){
        meishiRepo.insert(meishi)
        //Log de desenvolvimento... não estavam aparecendo Cards na tela principal.. eu tinha posto um finish() no lugar errado...
        //Log.i("click", "Executada a função de salvar")
    }

    //Função que retorna todos os cartões.
    fun getAll(): LiveData<List<Meishi>> {
        return meishiRepo.getAll()
    }
}

//Injetando o view model num provider
// TODO: 25/08/2021 "Fazer a injeção de dependencias aqui para não ser feito dentro do arquivo do viewmodel.

class MainViewModelFactory(private val repository: MeishiRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}