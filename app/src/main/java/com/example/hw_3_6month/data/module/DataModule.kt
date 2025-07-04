package com.example.hw_3_6month.data.module

import com.example.hw_3_6month.BuildConfig
import com.example.hw_3_6month.data.api.CharacterApiService
import com.example.hw_3_6month.data.repository.CharacterRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single { provideLoggingInterceptor() }
    single{ provideRetrofit(okHttpClient = get())}
    single{ provideOkhttpClient(loggingInterceptor = get())}

    single<CharacterApiService>{get<Retrofit>().create(CharacterApiService::class.java)}

    single { CharacterRepository(characterApiService = get ()) }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
}

private fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient{
        return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}
private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}