package com.ktu.nearfuel.di.module


import android.app.Application
import androidx.navigation.Navigator
import com.ktu.nearfuel.BuildConfig
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.network.AuthInterceptor
import com.ktu.nearfuel.network.AuthRepository
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
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
    @Named("unauthorized")
    internal fun getApiInterfaceUnauthorized(@Named("unauthorized") retroFit: Retrofit): APIInterface {
        return retroFit.create<APIInterface>(APIInterface::class.java!!)
    }

    @Provides
    @Singleton
    @Named("unauthorized")
    internal fun getRetrofitUnauthorized(@Named("unauthorized") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            ) //
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            ) //
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun getOkHttpCleint(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("unauthorized")
    internal fun getOkHttpCleintUnauthorized(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun getAuthRepository(application: Application): AuthRepository {
        return AuthRepository(application)
    }

    @Provides
    @Singleton
    internal fun getAuthInterceptor(authRepository: AuthRepository): AuthInterceptor {
        return AuthInterceptor(authRepository)
    }
}
