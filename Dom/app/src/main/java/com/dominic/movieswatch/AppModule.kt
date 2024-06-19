package com.dominic.movieswatch
import com.dominic.movieswatch.api.ApiService
import com.dominic.movieswatch.repository.MovieRepository
import com.dominic.movieswatch.viewmodel.FavoriteViewModel
import com.dominic.movieswatch.viewmodel.MovieViewModel
import com.dominic.movieswatch.viewmodel.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val baseUrl = "https://api.themoviedb.org/3/"

val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}



val appModule = module {
    single { networkModule  }
    single {
        MovieRepository(get())
        viewModel { MovieViewModel(get()) }
        viewModel { SearchViewModel(get()) }
        viewModel { FavoriteViewModel(get()) }
    }
}

