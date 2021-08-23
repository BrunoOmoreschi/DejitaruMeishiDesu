package com.github.brunoomoreshi.dejitarumeishidesu.ui

import android.Manifest
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.brunoomoreshi.dejitarumeishidesu.R
import androidx.core.app.ActivityCompat
import com.github.brunoomoreshi.dejitarumeishidesu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //ViewBinding ativo no gradle app:**
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}

/*
* * Eu sei que o material design recomenda uso de FAB mas queria dar uma investida num app bunitão dessa vez ;-)
* * Facilita a gestão de nullabity e elimina a necessidade de procurar usando o findViewById(R.id.xxxxxx), segundo a aula é recomendado pelo google.
*
* * Codigos para multithread (bkp):
* Thread.sleep(3000) //Bloqueia a execução
* Thread(Runnable{"Coloque o código a executar aqui"}).start()
*Se tiver elementos de UI coloque dentro dessa thread acima: runOnUiThread{"Coloque o código de UI aqui!"}
* */