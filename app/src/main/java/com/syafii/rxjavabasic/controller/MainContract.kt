package com.syafii.rxjavabasic.controller
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import com.syafii.rxjavabasic.base.BasePresenter
import com.syafii.rxjavabasic.base.BaseView
import com.syafii.rxjavabasic.model.User

interface MainContract {
    interface View : BaseView {
        fun showListUser(user: List<User>)
        fun showListUserPaging(totalPage: Int)
        fun showError(message: String)
    }

    interface Presenter : BasePresenter {
        fun getListUser(per_page: Int, page: Int)
    }
}