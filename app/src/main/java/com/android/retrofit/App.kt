package com.android.retrofit

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            viewModel {
                MainViewModel(repo = get())
            }
        }
        val repositories = module {
            single<Repo> {
                UserSignInRequestModelImpl(get ())
            }
        }

        startKoin {
            androidContext(applicationContext)
            modules(
                myModule, repositories
            )
        }
    }
}