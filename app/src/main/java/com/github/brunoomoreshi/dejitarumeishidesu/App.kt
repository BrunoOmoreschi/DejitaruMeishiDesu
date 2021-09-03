package com.github.brunoomoreshi.dejitarumeishidesu

import android.app.Application
import com.github.brunoomoreshi.dejitarumeishidesu.data.AppDatabase
import com.github.brunoomoreshi.dejitarumeishidesu.data.MeishiRepo

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { MeishiRepo(database.meishiDao()) }
}