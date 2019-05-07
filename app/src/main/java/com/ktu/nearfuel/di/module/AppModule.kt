package com.ktu.nearfuel.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ktu.components.data.Database
import com.ktu.components.data.GasStationDao
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application


    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()


    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "db").build()
    @Provides
    @Singleton
        internal fun provideGasStationDao(appDatabase: Database): GasStationDao = appDatabase.gasStationDao()

}