package com.github.brunoomoreshi.dejitarumeishidesu.ui
//Pacote que pega os dados oriundos da lista e os lista na tela para o usuario.

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.brunoomoreshi.dejitarumeishidesu.data.Meishi
import com.github.brunoomoreshi.dejitarumeishidesu.databinding.ItembcBinding

class meishiAdapter :
    ListAdapter<Meishi, meishiAdapter.ViewHolder>(DiffCallback()) {

    //Notifica quando clicado.
    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  ItembcBinding .inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItembcBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Meishi) {
            binding.tvNome.text = item.nome
            binding.tvPhone.text = item.telefone
            binding.tvEmail.text = item.email
            binding.tvCompanyName.text = item.empresa
            binding.cdContent.setOnClickListener {
                listenerShare(it)
            }
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<Meishi>() {
    override fun areItemsTheSame(oldItem: Meishi, newItem: Meishi) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Meishi, newItem: Meishi) =
        oldItem.id == newItem.id
}