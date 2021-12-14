package com.example.arivetsamplechallenge.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.arivetsamplechallenge.R
import com.example.arivetsamplechallenge.adapter.UserDetailsPagingDataAdapter
import com.example.arivetsamplechallenge.base.BaseFragment
import com.example.arivetsamplechallenge.databinding.FragmentHomeBinding
import com.example.arivetsamplechallenge.ui.*
import com.example.arivetsamplechallenge.ui.activities.MainActivity
import com.example.arivetsamplechallenge.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val userViewModel: UserViewModel by activityViewModels()

    @Inject
    lateinit var pagingAdapter: UserDetailsPagingDataAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).apply {
            if(!isNetworkAvailable()) makeToast(getString(R.string.no_internet_error))
            else {
                userViewModel.getDataFromRemoteSource()
                initRecyclerView()
                userViewModel._uiState.observe(this, uiStateObserver)
            }
        }

    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = pagingAdapter.apply {
                onItemClick = { pos: Int ->
                    (activity as MainActivity).navigateTo(
                        R.id.action_homeFragment_to_userDetailsFragment,
                        (pos).toLong()
                    )
                }
            }
        }
    }

    private val uiStateObserver = Observer<UiState> {
        when (it) {
            LoadingState -> binding.progressCircular.visibility=View.VISIBLE
            ContentState -> {
                binding.progressCircular.visibility=View.GONE
                lifecycleScope.launchWhenCreated {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        userViewModel.geUserstListOfData()
                            .collectLatest { source -> pagingAdapter.submitData(source)

                            }

                    }
                }
            }
            ErrorState ->binding.progressCircular.visibility=View.GONE
        }
    }
    private fun makeToast(message:String){
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }
}