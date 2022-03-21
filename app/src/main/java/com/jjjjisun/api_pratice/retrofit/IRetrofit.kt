package com.jjjjisun.api_pratice.retrofit

import com.google.gson.JsonElement
import com.jjjjisun.api_pratice.utills.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {

    // https://www.unsplash.com/search/photos/?query=" "

    @GET(API.SEARCH_PHOTO)
    fun searchPhotos(@Query("query") searchTerm: String): Call<JsonElement>

    @GET(API.SEARCH_USERS)
    fun searchUsers(@Query("query") searchTerm: String): Call<JsonElement>

}