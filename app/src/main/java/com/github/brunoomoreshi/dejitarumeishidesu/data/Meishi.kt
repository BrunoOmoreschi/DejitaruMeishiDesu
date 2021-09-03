package com.github.brunoomoreshi.dejitarumeishidesu.data
//Pacote que cria o objeto "Cartão de visita" [meishi]. Define o que vai ser para cada cadastro.

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Meishi (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String?,
    val empresa: String?,
    val telefone: String?,
    val email:  String?,
)