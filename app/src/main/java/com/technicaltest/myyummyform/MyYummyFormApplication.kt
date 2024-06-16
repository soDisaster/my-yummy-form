package com.technicaltest.myyummyform

import android.app.Application
import com.technicaltest.myyummyform.activity.MainActivity
import com.technicaltest.myyummyform.activity.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyYummyFormApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyYummyFormApplication)
            androidLogger()
            androidFileProperties()
            modules(
                module {
                    factory { MainActivityViewModel() }
                }
            )
        }
    }
}