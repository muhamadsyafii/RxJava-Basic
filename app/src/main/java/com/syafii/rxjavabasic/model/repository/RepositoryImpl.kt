package com.syafii.rxjavabasic.model.repository
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import com.syafii.rxjavabasic.model.BaseResponse
import com.syafii.rxjavabasic.network.ApiRest
import io.reactivex.Observable

class RepositoryImpl(private val apiRest: ApiRest) : Repository {
    override fun getUserList(per_page : Int, page : Int): Observable<BaseResponse> {
        return apiRest.getUserList(per_page, page)
    }
}