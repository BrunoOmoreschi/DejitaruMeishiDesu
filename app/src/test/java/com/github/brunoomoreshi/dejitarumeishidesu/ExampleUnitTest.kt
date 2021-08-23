package com.github.brunoomoreshi.dejitarumeishidesu

import org.junit.Test
import org.junit.Assert.*

//Imports custom for test
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ApplicationProvider

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}


/*
* Anotações da implementação:
* - Main activity: Mostra os cartões de visita: RecyclerView
* - Add activity: Cadastra novos cartões de visita: Formulário
* - Envio do cartão em redes sociais
* */