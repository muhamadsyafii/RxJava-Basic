package com.syafii.rxjavabasic.controller
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import com.syafii.rxjavabasic.model.repository.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private val view: MainContract.View,
    private val repositoryImpl: RepositoryImpl
) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getListUser(per_page: Int, page: Int) {
        view.showLoading()
        compositeDisposable.add(repositoryImpl.getUserList(per_page, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    view.hideLoading()
                    view.showListUser(it.data)
                    view.showListUserPaging(it.totalPage)

                }, {
                    view.hideLoading()
                    view.showError(it.message!!)
                }
            ))
    }

    override fun start() {
        view.initView()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}