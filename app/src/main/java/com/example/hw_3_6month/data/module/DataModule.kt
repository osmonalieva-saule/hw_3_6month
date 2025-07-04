package com.example.hw_3_6month.data.module

import com.example.hw_3_6month.BuildConfig
import com.example.hw_3_6month.data.api.CharacterApiService
import com.example.hw_3_6month.data.repository.CharacterRepository
import com.example.hw_3_6month.data.repository.EpisodeRepository
import com.example.hw_3_6month.data.repository.LocationRepository
import com.example.hw_3_6month.data.repository.LocationRepositoryImpl
import com.example.hw_3_6month.ui.screens.characters.CharacterDetailViewModel
import com.example.hw_3_6month.ui.screens.characters.CharacterViewModel
import com.example.hw_3_6month.ui.screens.episodes.EpisodeDetailViewModel
import com.example.hw_3_6month.ui.screens.episodes.EpisodeListViewModel
import com.example.hw_3_6month.ui.screens.locations.LocationDetailViewModel
import com.example.hw_3_6month.ui.screens.locations.LocationListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.androidx.viewmodel.dsl.viewModel


val dataModule = module {
    single { provideLoggingInterceptor() }
    single{ provideRetrofit(okHttpClient = get())}
    single{ provideOkhttpClient(loggingInterceptor = get())}

    single<CharacterApiService>{get<Retrofit>().create(CharacterApiService::class.java)}

    single { CharacterRepository(characterApiService = get ()) }
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single { EpisodeRepository(get()) }


    viewModel { CharacterViewModel(get()) }
    viewModel { CharacterDetailViewModel(repository = get()) }
    viewModel { EpisodeListViewModel(get()) }
    viewModel { EpisodeDetailViewModel(get()) }

    viewModel { LocationListViewModel(get()) }
    viewModel { LocationDetailViewModel(get()) }
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