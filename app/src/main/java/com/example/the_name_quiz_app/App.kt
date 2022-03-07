package com.example.the_name_quiz_app

import android.app.Application
import com.example.the_name_quiz_app.common.commonModule
import com.example.the_name_quiz_app.game.gameModule
import com.example.the_name_quiz_app.main.mainModule
import com.example.the_name_quiz_app.people.peopleModule
import com.example.the_name_quiz_app.startup.startupModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                mainModule,
                commonModule,
                startupModule,
                peopleModule,
                gameModule
            )
        }
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
