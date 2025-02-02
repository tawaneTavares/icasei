package com.example.icasei.di

import com.example.icasei.BuildConfig
import com.example.icasei.data.remote.IYoutubeApi
import com.example.icasei.data.remote.IYoutubeRemoteData
import com.example.icasei.data.remote.YoutubeRemoteData
import com.example.icasei.domain.repository.IYoutubeRepository
import com.example.icasei.domain.usecases.GetSearchUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofit(): IYoutubeApi {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("key", BuildConfig.YOUTUBE_API_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()

        val moshi =
            Moshi
                .Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(IYoutubeApi::class.java)
    }

    @Provides
    fun providesYoutubeRemoteData(apiClient: IYoutubeApi): IYoutubeRemoteData = YoutubeRemoteData(apiClient)

    @Provides
    fun providesGetSearchUseCase(repository: IYoutubeRepository): GetSearchUseCase = GetSearchUseCase(repository)
}
