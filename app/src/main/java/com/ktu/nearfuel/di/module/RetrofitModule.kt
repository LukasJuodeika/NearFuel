package com.ktu.nearfuel.di.module


import com.ktu.nearfuel.di.scopes.ApplicationScope
import com.ktu.nearfuel.network.APIInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    internal val httpLoggingInterceptor: HttpLoggingInterceptor
        @Provides
        @ApplicationScope
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

    @Provides
    @ApplicationScope
    internal fun getApiInterface(retroFit: Retrofit): APIInterface {
        return retroFit.create<APIInterface>(APIInterface::class.java!!)
    }

    @Provides
    @ApplicationScope
    internal fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinmarketcap.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @ApplicationScope
    internal fun getOkHttpCleint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}
