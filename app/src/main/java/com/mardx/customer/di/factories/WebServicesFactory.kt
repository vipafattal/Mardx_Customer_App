package com.mardx.customer.di.factories

import com.mardx.customer.data.webservices.TenantProductsService
import com.mardx.customer.data.webservices.common.BASE_URL
import com.mardx.customer.di.factories.WebServicesFactory.buildRetrofitService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

@Single
fun provideProductsService(): TenantProductsService {
    return buildRetrofitService(BASE_URL, TenantProductsService::class.java)
}

object WebServicesFactory {
    fun <T> buildRetrofitService(
        baseUrl: String,
        clazz: Class<T>,
        clientConfigs: OkHttpClient.Builder? = null
    ): T {
        val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = (clientConfigs ?: OkHttpClient.Builder())
            .addInterceptor(logger)
            .callTimeout(20000, TimeUnit.MILLISECONDS)

            .retryOnConnectionFailure(true)
            .build()

        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json; charset=UTF8".toMediaType())
            ).build()
            .create(clazz)
    }
}