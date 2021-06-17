package com.syafii.rxjavabasic.controller
/*
 * Created by Muhamad Syafii
 * Tuesday, 17/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syafii.rxjavabasic.R
import com.syafii.rxjavabasic.databinding.ItemUserListBinding
import com.syafii.rxjavabasic.model.User

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val list = mutableListOf<User>()

    fun addItems(listUser: List<User>) {
        list.clear()
        list.addAll(listUser)
        notifyDataSetChanged()
    }

    fun refreshAdapter(listUser: List<User>) {
        list.addAll(listUser)
        notifyItemChanged(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        return MainViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount() = list.size

    inner class MainViewHolder(private val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listUser: User) {
            binding.run {
                Glide.with(binding.root)
                    .load(listUser.avatar)
                    .error(R.drawable.ic_user)
                    .into(binding.siAvatar)
                tvName.text = "${listUser.firstName}  ${listUser.lastName}"
                tvEmail.text = listUser.email
            }
        }
    }
}
