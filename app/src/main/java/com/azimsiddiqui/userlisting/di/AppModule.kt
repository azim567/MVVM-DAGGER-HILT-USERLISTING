package com.azimsiddiqui.userlisting.di

import com.azimsiddiqui.userlisting.BuildConfig
import com.azimsiddiqui.userlisting.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//
//        private val okHttpClient = OkHttpClient()
//            .newBuilder()
//            .addInterceptor(run {
//                val httpLoggingInterceptor = HttpLoggingInterceptor()
//                httpLoggingInterceptor.apply {
//                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//                }
//            }).build()

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

    @Singleton
    @Provides
    fun getApi()= getRetrofitInstance().create<ApiService>(ApiService::class.java)

}