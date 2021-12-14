package com.example.arivetsamplechallenge.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.arivetsamplechallenge.api_response.UsersInfo
import com.example.arivetsamplechallenge.base.BaseFragment
import com.example.arivetsamplechallenge.databinding.FragmentUserDetailsBinding
import com.example.arivetsamplechallenge.utils.Constansts.Companion.CLICKED_POS
import com.example.arivetsamplechallenge.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate)  {
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getLong(CLICKED_POS)?.let {
            userViewModel.getSingleUserDetail(it)
        }
        userViewModel._userDetail.observe(this,userDetailObserver)
    }
    private val userDetailObserver=Observer<UsersInfo>{
        binding.apply {
            tvName.text=it.name.first
            tvPhoneNumber.text=it.cell
            Glide.with(context!!)
                .load(it.picture.medium)
                .fitCenter()
                .into(binding.imageView)
        }

    }
}