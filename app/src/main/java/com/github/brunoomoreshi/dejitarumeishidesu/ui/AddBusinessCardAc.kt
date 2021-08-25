package com.github.brunoomoreshi.dejitarumeishidesu.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.brunoomoreshi.dejitarumeishidesu.databinding.AddbusinessbardacBinding


class AddBusinessCardAc : AppCompatActivity() {

   private val binding by lazy {AddbusinessbardacBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }


    private fun insertListeners() {
        binding.ibClose.setOnClickListener {
            finish()
        }
    }


}