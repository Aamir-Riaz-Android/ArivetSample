package com.example.arivetsamplechallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.arivetsamplechallenge.api_response.UsersInfo
import com.example.arivetsamplechallenge.databinding.ListUserDetailsRowBinding
import javax.inject.Inject

class UserDetailsPagingDataAdapter @Inject constructor() :
    PagingDataAdapter<UsersInfo, UserDetailsPagingDataAdapter.UserPostViewHolder>(UserPostEntityDiff()) {
    var onItemClick: ((pos:Int) -> Unit)? = null
    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        getItem(position)?.let { userPostEntity -> holder.bind(userPostEntity) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder {
        return UserPostViewHolder(
            ListUserDetailsRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class UserPostViewHolder(private val binding: ListUserDetailsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userPostEntity: UsersInfo) {
            binding.tvName.text = userPostEntity.name.first
            binding.tvAddress.text = userPostEntity.location.country
            binding.imageView.apply {
                Glide.with(this)
                    .load(userPostEntity.picture.thumbnail)
                    .fitCenter()
                    .into(binding.imageView)

            }
            binding.root.setOnClickListener {
                onItemClick?.invoke(userPostEntity.myId.toInt())
            }
        }

    }

    class UserPostEntityDiff : DiffUtil.ItemCallback<UsersInfo>() {
        override fun areItemsTheSame(oldItem: UsersInfo, newItem: UsersInfo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UsersInfo, newItem: UsersInfo): Boolean =
            oldItem == newItem
    }
}