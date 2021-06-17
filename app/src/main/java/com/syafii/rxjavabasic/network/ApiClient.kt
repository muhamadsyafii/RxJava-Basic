package com.syafii.rxjavabasic.network
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private val interceptor by lazy {HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)}
    private val client by lazy { OkHttpClient.Builder().addInterceptor(interceptor).build()}
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val services by lazy { retrofit.create(ApiRest::class.java)}
}