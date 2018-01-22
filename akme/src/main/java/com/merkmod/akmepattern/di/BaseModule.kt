package com.merkmod.akmepattern.di

import android.content.SharedPreferences
import com.jakewharton.picasso.OkHttp3Downloader
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.picasso.Picasso
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.module.AndroidModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by thepunkprogrammer on 1/22/18.
 */
fun <T>BaseModules(baseUrl: String) = listOf(SharedPreferenceModule(), NetworkModule<T>(baseUrl))

class SharedPreferenceModule: AndroidModule() {
    override fun context() = applicationContext {
        provide {
            createSharePreference(this@SharedPreferenceModule.context)
        }
    }

}

class NetworkModule<T>(val baseUrl: String): AndroidModule() {
    override fun context() = applicationContext {
        provide { createOkttpClient(this@NetworkModule.context) }
        provide { createPicasso(this@NetworkModule.context, get()) }
        provide { createWebService<Class<T>>(okHttpClient = get(), baseUrl = baseUrl) }
    }
}



fun createSharePreference(context: android.content.Context) : SharedPreferences
        = context.getSharedPreferences(context.packageName, android.content.Context.MODE_PRIVATE)


fun createOkttpClient(context: android.content.Context): OkHttpClient {
    val cache = (Cache(File(context.cacheDir,"okhttp_cache"), 10 * 1000 * 1000))
    val httpLogingInterceptor = HttpLoggingInterceptor()
    httpLogingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLogingInterceptor)
            .cache(cache)
            .build()
}

fun createPicasso(context: android.content.Context, okHttpClient: OkHttpClient): Picasso {
    val okhttp3Downloader = OkHttp3Downloader(okHttpClient)
    return Picasso.Builder(context)
            .downloader(okhttp3Downloader)
            .build()
}


inline fun <reified T> createWebService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    return retrofit.create(T::class.java)
}