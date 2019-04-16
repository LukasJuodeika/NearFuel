package com.ktu.nearfuel.di.component

import android.app.Application
import com.ktu.nearfuel.MvpApp
import com.ktu.nearfuel.di.builder.ActivityBuilder
import com.ktu.nearfuel.di.module.AppModule
import com.ktu.nearfuel.di.module.RetrofitModule
import com.ktu.nearfuel.network.APIInterface

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by jyotidubey on 05/01/18.
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (AppModule::class),
    (RetrofitModule::class),
    (ActivityBuilder::class)])
interface AppComponent {
    fun getApiInterface(): APIInterface
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: MvpApp)

}