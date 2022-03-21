package com.jjjjisun.api_pratice.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 싱글턴
object RetrofitClient {

    // 레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null // 1번
    //private lateinit var retrofitClient: Retrofit // 2번

    // 레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {
        Log.d("태그", "레트로핏 클라이언트 - getClient() called")

        // 로깅 인터셉터
        // okhttp 인스턴스 생성
        val client = OkHttpClient.Builder()

        // 로그를 찍기위해 로깅 인터셉터 추가
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{

            override fun log(message: String) {
                Log.d("태그", "로그가 호출됨 / message: $message")

            }
        })

        // 기본 파라미터 추가


        // 레트로핏 클라이언트에 값이 없을 때 새로 생성
        if (retrofitClient == null) {

            // 레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient
    }

}