package com.android.fundallapp.auth.di

import com.android.fundallapp.auth.data.remotedata.network.AuthApiInterface
import com.android.fundallapp.auth.domain.repository.AuthRepository
import com.android.fundallapp.auth.domain.repository.AuthRepositoryImpl
import com.android.fundallapp.utils.Contants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthNetworkModule {

    @Provides
    @Singleton
    fun providesRetrofitInstance(baseUrl: String): Retrofit {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val interceptor = OkHttpClient.Builder()
            .addInterceptor(logger).build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(interceptor)

        return retrofit.build()
    }

    @Provides
    @Singleton
    fun provideAuthModuleApi(): AuthApiInterface {
        return providesRetrofitInstance(BASE_URL).create(AuthApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(authApiInterface: AuthApiInterface): AuthRepositoryImpl =
        AuthRepository(authApiInterface)

}