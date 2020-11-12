package com.brainvire.brainviretestapp.APIConfiguration

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIManagement {

    //call to get default movie list
    @GET("history")
    fun getDataList(@Query("start_at") start_at: String?,
                     @Query("end_at") end_at:String,
                     @Query("base") base:String?): Call<Any?>?



}