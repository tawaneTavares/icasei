package com.example.icasei.di

import android.content.Context
import androidx.room.Room
import com.example.icasei.BuildConfig
import com.example.icasei.data.local.IVideoLocalData
import com.example.icasei.data.local.IcaseiDatabase
import com.example.icasei.data.local.VideoLocalData
import com.example.icasei.data.remote.IYoutubeRemoteData
import com.example.icasei.data.remote.YoutubeRemoteData
import com.example.icasei.data.remote.api.IYoutubeApi
import com.example.icasei.data.repository.YoutubeRepository
import com.example.icasei.domain.repository.IYoutubeRepository
import com.example.icasei.domain.usecase.AddFavoriteUseCase
import com.example.icasei.domain.usecase.CheckFavoriteUseCase
import com.example.icasei.domain.usecase.DeleteFavoriteUseCase
import com.example.icasei.domain.usecase.GetFavoriteUseCase
import com.example.icasei.domain.usecase.GetSearchUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi): IYoutubeApi {
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

        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(IYoutubeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStarWarsDatabase(@ApplicationContext context: Context): IcaseiDatabase {
        return Room.databaseBuilder(
            context,
            IcaseiDatabase::class.java,
            "icasei.db",
        ).build()
    }

    @Provides
    fun providesYoutubeRemoteData(apiClient: IYoutubeApi): IYoutubeRemoteData = YoutubeRemoteData(apiClient)

    @Provides
    fun providesVideoLocalData(appDatabase: IcaseiDatabase): IVideoLocalData = VideoLocalData(appDatabase)

    @Provides
    fun providesYoutubeRepository(remoteData: IYoutubeRemoteData, localData: IVideoLocalData): IYoutubeRepository =
        YoutubeRepository(remoteData, localData)

    @Provides
    fun providesGetSearchUseCase(repository: IYoutubeRepository): GetSearchUseCase = GetSearchUseCase(repository)

    @Provides
    fun providesAddFavoriteUseCase(repository: IYoutubeRepository): AddFavoriteUseCase = AddFavoriteUseCase(repository)

    @Provides
    fun providesCheckFavoriteUseCase(repository: IYoutubeRepository): CheckFavoriteUseCase = CheckFavoriteUseCase(repository)

    @Provides
    fun providesDeleteFavoriteUseCase(repository: IYoutubeRepository): DeleteFavoriteUseCase = DeleteFavoriteUseCase(repository)

    @Provides
    fun providesGetFavoriteUseCase(repository: IYoutubeRepository): GetFavoriteUseCase = GetFavoriteUseCase(repository)
}
