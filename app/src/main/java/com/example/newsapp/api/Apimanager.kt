package com.example.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class apimanager {
    companion object {
        private var retrofit: Retrofit? = null
        @Synchronized
       private fun getinstance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit!!
            }
            return retrofit!!
        }
        fun getwebservices(): WebServices {
            return getinstance().create(WebServices::class.java)
        }
    }



}