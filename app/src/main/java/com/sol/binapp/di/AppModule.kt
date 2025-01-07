package com.sol.binapp.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sol.binapp.BASE_URL
import com.sol.binapp.RETROFIT_MEDIA_TYPE
import com.sol.binapp.createOkHttpClient
import com.sol.binapp.data.AppDatabase
import com.sol.binapp.data.BinApiService
import com.sol.binapp.data.BinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database",
        ).build()

    @Provides
    @Singleton
    fun provideBinDao(appDatabase: AppDatabase): BinDao = appDatabase.binDao()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }

        val converterType: MediaType = RETROFIT_MEDIA_TYPE.toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(json.asConverterFactory(converterType))
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeApiService(retrofit: Retrofit): BinApiService {
        return retrofit.create(BinApiService::class.java)

    }
}
