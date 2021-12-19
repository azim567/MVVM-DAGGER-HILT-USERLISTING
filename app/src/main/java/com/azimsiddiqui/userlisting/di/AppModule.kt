package com.azimsiddiqui.userlisting.di

import com.azimsiddiqui.userlisting.data.api.ApiService
import com.azimsiddiqui.userlisting.data.repository.UserRepositoryImpl
import com.azimsiddiqui.userlisting.data.repository.datasource.UserRemoteDataSource
import com.azimsiddiqui.userlisting.data.repository.datasourceimpl.UserRemoteDataSourceImpl
import com.azimsiddiqui.userlisting.di.Constants.BASE_URL
import com.azimsiddiqui.userlisting.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

    @Singleton
    @Provides
    fun getApi()= getRetrofitInstance().create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideRemoteUserDataSource(apiService: ApiService): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(userRemoteDataSource)
    }

}