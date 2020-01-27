package com.elimak.demo.api

import android.content.Context
import android.util.Log
import com.elimak.demo.R
import com.elimak.demo.db.vo.NewsFeed
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiServices {

    data class NewsResponse(val articles: List<NewsFeed> = emptyList(), val totalResults: Int = 0, val status: String = "")

    // top-headlines
    // country=us
    @GET("top-headlines")
    suspend fun getTopArticles(@Query("country") country: String) : NewsResponse

    companion object Factory {
        const val BASE_URL = "https://newsapi.org/v2/"

        private val httpClient = OkHttpClient.Builder()

        fun create(context: Context): IApiServices {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            httpClient.addInterceptor(interceptor)

            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.addInterceptor{chain->
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
                            .header("X-Api-Key", context.getString(R.string.api_key))
                        val request = requestBuilder.build()
                        chain.proceed(request)
                    }.build())
                .build()

            return retrofit.create(IApiServices::class.java)
        }
    }
}