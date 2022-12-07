package com.production.myapp

import com.production.myapp.core.CustomInterceptor
import com.production.myapp.core.constants.Urls
import com.production.myapp.data.data_sources.MovieRemoteDataSource
import com.production.myapp.data.repositories.MovieRepositoryImpl
import com.production.myapp.domain.repositories.MovieRepository
import com.production.myapp.presentation.global_state.AppViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideAppViewModel(): AppViewModel {
        return AppViewModel()
    }

    @Provides
    @Singleton
    fun provideOkHttpInterceptor(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(CustomInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(okHttpClient: OkHttpClient): MovieRemoteDataSource {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Urls.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieRemoteDataSource::class.java)
    }


    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: MovieRemoteDataSource): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource)
    }
}