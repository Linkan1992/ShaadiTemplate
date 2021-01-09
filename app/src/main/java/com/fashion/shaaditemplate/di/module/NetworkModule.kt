package com.fashion.shaaditemplate.di.module

import android.app.Application
import com.fashion.shaaditemplate.BuildConfig
import com.fashion.shaaditemplate.data.network.ApiService
import com.fashion.shaaditemplate.util.constUtil.AppConstants
import com.fashion.shaaditemplate.util.requestUtil.RequestKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): ApiService =
        createService(retrofit)


    //  private fun <T> createService(retrofit: Retrofit, clazz: Class<T>): T = retrofit.create(clazz)

    private inline fun <reified T> createService(retrofit: Retrofit): T =
        retrofit.create(T::class.java)

    @Provides
    @Singleton
    internal fun provideCoroutineRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }


    @Provides
    @Singleton
    internal fun provideOKHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor, application: Application
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }


    @Provides
    @Singleton
    internal fun provideLoggingInt(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    internal fun provideHeaderInt(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader(RequestKey.CONTENT_TYPE, RequestKey.CONTENT_VALUE)
                .addHeader(RequestKey.ACCEPT_KEY, RequestKey.ACCEPT_VALUE)
                .build()

            chain.proceed(request)
        }
    }


    @Provides
    @Singleton
    internal fun provideConverter(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)


    @Provides
    @Singleton
    internal fun provideGson(): Gson = GsonBuilder().create()

}