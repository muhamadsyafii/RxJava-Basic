package com.syafii.rxjavabasic.network
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import com.syafii.rxjavabasic.model.BaseResponse
import com.syafii.rxjavabasic.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRest {
    @GET("/api/users")
    fun getUserList(
        @Query("per_page") per_page : Int,
        @Query("page") page : Int
    ): Observable<BaseResponse>
}