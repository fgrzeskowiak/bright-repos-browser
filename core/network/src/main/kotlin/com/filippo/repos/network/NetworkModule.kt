package com.filippo.repos.network

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val cacheSize = 10 * 10 * 1024L
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun cache(@ApplicationContext context: Context): Cache {
        val cachePath = File(context.cacheDir, "http-cache")
        return Cache(cachePath, cacheSize)
    }

    @Provides
    @Singleton
    fun okHttpClient(cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .cache(cache)
            .build()

    @Provides
    @Singleton
    fun jsonConverterFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return json.asConverterFactory(contentType)
    }

    @Provides
    @Singleton
    fun retrofitBuilder(
        client: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(converterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
}
