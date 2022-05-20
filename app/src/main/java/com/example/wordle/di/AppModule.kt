package com.example.wordle.di

import com.example.wordle.network.MyWordleApi
import com.example.wordle.repo.MyWordleRepo
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

    @Provides
    @Singleton
    fun provideWordleRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://6282cfe392a6a5e4621a01f6.mockapi.io/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    @Provides
    @Singleton
    fun provideWordleApi(retrofit: Retrofit): MyWordleApi = retrofit.create(MyWordleApi::class.java)

    /*@Provides
    @Singleton
    fun provideWordleRepo(myWordleApi: MyWordleApi): MyWordleRepo{
        return MyWordleRepo(myWordleApi)
    }*/

}