package com.syafii.rxjavabasic.controller
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syafii.rxjavabasic.databinding.ActivityMainBinding
import com.syafii.rxjavabasic.model.User
import com.syafii.rxjavabasic.model.repository.RepositoryImpl
import com.syafii.rxjavabasic.network.ApiClient
import com.syafii.rxjavabasic.util.PER_PAGE

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    private var isLoading: Boolean = false
    private var page: Int = 1
    private var totalPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MainAdapter()
        presenter = MainPresenter(this, RepositoryImpl(ApiClient.services))
        presenter.start()
    }

    override fun showListUser(user: List<User>) {
        if (user.isNotEmpty()) {
            if (page == 1) {
                adapter.addItems(user)
                binding.rvList.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                binding.rvList.adapter = adapter
            } else {
                adapter.refreshAdapter(user)
            }
        } else {
            binding.tvEmptyData.visibility = View.VISIBLE
        }
    }

    override fun showListUserPaging(totalPage: Int) {
        this.totalPage = totalPage

        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = binding.rvList.layoutManager as LinearLayoutManager
                val countItem = linearLayoutManager.itemCount
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition

                if (!isLoading && isLastPosition && page < totalPage) {
                    setLoadingState(true)
                    page = page.plus(1)
                    presenter.getListUser(PER_PAGE, page)
                }
            }
        })
    }

    private fun setLoadingState(isRefresh: Boolean) {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
        binding.rvList.visibility.let {
            when (isRefresh) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun initView() {
        presenter.getListUser(PER_PAGE, page)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        isLoading = false
        binding.progressBar.visibility = View.GONE
        binding.rvList.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        presenter.getListUser(PER_PAGE, page)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}