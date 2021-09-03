package com.github.brunoomoreshi.dejitarumeishidesu.ui

import android.Manifest
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.github.brunoomoreshi.dejitarumeishidesu.App
import com.github.brunoomoreshi.dejitarumeishidesu.R
import com.github.brunoomoreshi.dejitarumeishidesu.databinding.ActivityMainBinding

import androidx.core.app.ActivityCompat

//Não sei o porque de o meu android studio não querer importar o viewModels.. então eu copiei de outro projeto e criei uma função local para servir de step e fazer a mesma coisa.
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.github.brunoomoreshi.dejitarumeishidesu.util.Image

//import com.github.brunoomoreshi.dejitarumeishidesu.util.Image

class MainActivity : AppCompatActivity() {

    //ViewBinding ativo no gradle app:**
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    //Função que injeta o repositório dentro do viewmodel
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    //Função step copiada de outro projeto pois o meu android studio não quer importar a função viewModels.
    public inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }

        return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
    }

    private val adapter by lazy { meishiAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //Havia um erro de exposição de URI q foi resolvido com a adição de requisição de acesso de leitura e gravação.
        setUpPermissions()
        binding.rvMeishi.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun listaMockUp(){
        //Lista apenas para testes automatizados.
    }

    private fun setUpPermissions() {
        // write permission to access the storage
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
    }

    private fun insertListener(){
        binding.ibAdd.setOnClickListener{
            val intent = Intent(this, AddBusinessCardAc::class.java)
            startActivity(intent)

        }



        adapter.listenerShare = {card ->
            Image.share(this@MainActivity, card)

        }
    }

    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this, { businessCards ->
            adapter.submitList(businessCards)
        })
    }

}

// TODO: 25/08/2021 Adicionar gerenciamento das Threads, nas notas aí embaixo tem o funcionamento dos códigos, só implementar e partir pro abraço.

/*
*  Eu sei que o material design recomenda uso de FAB mas queria dar uma investida num app bunitão dessa vez ;-)
* * Facilita a gestão de nullabity e elimina a necessidade de procurar usando o findViewById(R.id.xxxxxx), segundo a aula é recomendado pelo google.
*
* * Codigos para multithread (bkp):
* Thread.sleep(3000) //Bloqueia a execução
* Thread(Runnable{"Coloque o código a executar aqui"}).start()
*Se tiver elementos de UI coloque dentro dessa thread acima: runOnUiThread{"Coloque o código de UI aqui!"}
* */