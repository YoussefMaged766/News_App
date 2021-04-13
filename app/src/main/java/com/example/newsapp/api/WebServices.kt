package com.example.newsapp.api

import com.example.newsapp.SoursesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("sources")
    fun getsourses (@Query("country ") country:String , @Query("apiKey") api_key:String):Call<SoursesResponse>

    @GET("everything")
    fun getarticals(@Query("q") q :String ,
                    @Query("apiKey") api_key:String ,
                    @Query("language") language :String,
                    @Query("sources") source : String
    ):Call<ArticalResponse>
}