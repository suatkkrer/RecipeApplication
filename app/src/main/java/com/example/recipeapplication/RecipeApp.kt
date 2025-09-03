package com.example.recipeapplication

import android.app.Application
import com.example.recipeapplication.di.appModule
import com.example.recipeapplication.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class RecipeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@RecipeApp)
            modules(
                appModule,
                networkModule
            )
        }
    }
}