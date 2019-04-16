package com.ktu.nearfuel.di.module

import android.app.Application
import android.content.Context
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


}