package com.dominic.movieswatch.di

import com.dominic.movieswatch.api.ApiService
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.utils.baseUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModuleInterface {
    fun getRetrofitInstance(api_key: String): ApiService
}

class AppModule : AppModuleInterface {

    override fun getRetrofitInstance(api_key: String): ApiService {

        val interceptor = TokenInterceptor(API_KEY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api : ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
        return api
    }
}
class TokenInterceptor(private val api_key: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req =
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $api_key")
                .build()
        return chain.proceed(req)
    }


  /*  val networkModule: Module = module {
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
    val databaseModules: Module = module {
        single { AppDatabase.getDatabase(get()) }
        single { get<AppDatabase>().movieDao() }
    }

    val appModules = listOf(networkModule, repositoryModule, viewModelModule, databaseModules)
 */
}
