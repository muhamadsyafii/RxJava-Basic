package com.syafii.rxjavabasic.model
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import com.squareup.moshi.Json

data class Support(
    @Json(name = "url")
    val url: String,
    @Json(name = "text")
    val text: String
)