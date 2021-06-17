package com.syafii.rxjavabasic.model
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import com.squareup.moshi.Json

data class BaseResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "total")
    val total: Int,
    @Json(name = "total_pages")
    val totalPage: Int,
    @Json(name = "data")
    val data: List<User>,
    @Json(name = "support")
    val support : Support
)