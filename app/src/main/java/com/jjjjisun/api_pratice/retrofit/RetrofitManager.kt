package com.jjjjisun.api_pratice.retrofit

import android.util.Log
import com.google.gson.JsonElement
import com.jjjjisun.api_pratice.utills.API
import com.jjjjisun.api_pratice.utills.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Response
import retrofit2.create

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    // http 콜 만들기
    // 레트로핏 인터페이스 가져오기
    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    // 사진 검색 api 호출출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE, String) -> Unit) {

        // 언래핑 작업
        val term = searchTerm.let {
            it
        } ?: ""

        val call = iRetrofit?.searchPhotos(searchTerm = term).let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d("태그", "응답에 성공했습니다. /response : ${response.body()}")

                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            // 응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("태그", "응답에 실패했습니다. /t: $t")

                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })
    }
}










