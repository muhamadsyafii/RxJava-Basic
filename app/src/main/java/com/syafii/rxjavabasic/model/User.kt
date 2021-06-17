package com.syafii.rxjavabasic.model
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import com.squareup.moshi.Json

data class User(
    @Json(name = "id")
    val id : Int,
    @Json(name = "email")
    val email : String,
    @Json(name = "first_name")
    val firstName : String,
    @Json(name = "last_name")
    val lastName : String,
    @Json(name = "avatar")
    val avatar : String
)