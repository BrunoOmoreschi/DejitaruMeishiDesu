package com.github.brunoomoreshi.dejitarumeishidesu.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.github.brunoomoreshi.dejitarumeishidesu.App
import com.github.brunoomoreshi.dejitarumeishidesu.R
import com.github.brunoomoreshi.dejitarumeishidesu.data.Meishi
import com.github.brunoomoreshi.dejitarumeishidesu.databinding.AddbusinessbardacBinding


class AddBusinessCardAc : AppCompatActivity() {

   private val binding by lazy {AddbusinessbardacBinding.inflate(layoutInflater)}

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        binding.ibClose.setOnClickListener {
            finish()
        }
        //Conexão da IU com objeto meishi
        binding.btConfirm.setOnClickListener{
            val businessCard = Meishi(
                nome = binding.etName.text.toString(),
                empresa = binding.etCompany.text.toString(),
                telefone = binding.etPhone.text.toString(),
                email = binding.etEmail.text.toString(),
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.addToast_Txt, Toast.LENGTH_SHORT).show()
            //Fecha a tela e volta a main depois de cadastrado um cartão.
            finish()
        }
    }


}