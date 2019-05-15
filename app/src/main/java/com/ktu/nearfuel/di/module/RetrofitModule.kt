package com.ktu.nearfuel.di.module


import com.ktu.nearfuel.di.scopes.ApplicationScope
import com.ktu.nearfuel.network.APIInterface
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    internal val httpLoggingInterceptor: HttpLoggingInterceptor
        @Provides
        @Singleton
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

    @Provides
    @Singleton
    internal fun getApiInterface(retroFit: Retrofit): APIInterface {
        return retroFit.create<APIInterface>(APIInterface::class.java!!)
    }

    @Provides
    @Singleton
    internal fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())) //
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun getOkHttpCleint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}
