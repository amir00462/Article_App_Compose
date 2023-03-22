package ir.dunijet.article_app_compose.ui

import android.app.Application
import ir.dunijet.article_app_compose.di.myModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp :Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(myModules)
        }

    }

}