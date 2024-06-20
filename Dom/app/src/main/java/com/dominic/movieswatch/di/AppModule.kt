package com.dominic.movieswatch.di

import com.dominic.movieswatch.api.ApiService
import com.dominic.movieswatch.repository.MovieRepository
import com.dominic.movieswatch.viewmodel.FavoriteViewModel
import com.dominic.movieswatch.viewmodel.MovieViewModel
import com.dominic.movieswatch.viewmodel.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModuleInterface {
    fun getRetrofitInstance(api_key: String): ApiService
}

class AppModule : AppModuleInterface {

    override fun getRetrofitInstance(api_key: String): ApiService {
        val baseUrl = "https://api.themoviedb.org/3/"

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("API_KEY", api_key)
                    .build()
                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}

val networkModule: Module = module {
    single { (api_key: String) -> AppModule().getRetrofitInstance(api_key) }
}

val repositoryModule: Module = module {
    single { MovieRepository(get(), get()) }
}

val viewModelModule: Module = module {
    viewModel { MovieViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}

val appModules = listOf(networkModule, repositoryModule, viewModelModule)
