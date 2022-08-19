package com.turtlemint.assignment

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.turtlemint.assignment.datasource.local.LocalDataSource
import com.turtlemint.assignment.datasource.local.database.AppDatabase
import com.turtlemint.assignment.datasource.remote.RemoteDataSource
import com.turtlemint.assignment.viewmodel.LocalViewModel
import com.turtlemint.assignment.viewmodel.RemoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    companion object {
        private const val PREFERENCES_KEY = "myAppPrefs"
        private const val APP_DATABASE_NAME = "database"
    }

    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, APP_DATABASE_NAME
        ).build()
        startKoin {
            printLogger()
            androidContext(this@MyApplication)
            modules(module {
                single { provideSharedPreferences(this@MyApplication) }
                single { RemoteDataSource() }
                single { LocalDataSource(db) }

                viewModel { RemoteViewModel(get(), get()) }
                viewModel { LocalViewModel(get()) }

            })
        }

    }

    private fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
}