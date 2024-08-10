package com.example.atlysassignment.di

import android.content.Context
import android.content.res.Resources
import com.example.atlysassignment.R
import com.example.atlysassignment.data.MovieRepositoryImpl
import com.example.atlysassignment.data.MovieService
import com.example.atlysassignment.domain.MovieRepository
import com.example.atlysassignment.utils.AppConstant
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideResource(@ApplicationContext context: Context): Resources = context.resources

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(resources: Resources): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(createAuthInterceptor(resources))
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
//            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService  {
        return retrofit.create(MovieService::class.java)
    }
    @Provides
    @Singleton
    fun providePicasso(): Picasso = Picasso.get()

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {
        @Binds
        @Singleton
        fun bindRepository(repositoryImpl: MovieRepositoryImpl) : MovieRepository
    }


    private fun createAuthInterceptor(resources: Resources): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${AppConstant.API_KEY}")
                .build()
            val response = chain.proceed(request)
            response
        }
    }

}