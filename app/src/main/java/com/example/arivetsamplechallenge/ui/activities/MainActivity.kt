package com.example.arivetsamplechallenge.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.arivetsamplechallenge.R
import com.example.arivetsamplechallenge.adapter.UserDetailsPagingDataAdapter
import com.example.arivetsamplechallenge.base.BaseActivity
import com.example.arivetsamplechallenge.databinding.ActivityMainBinding
import com.example.arivetsamplechallenge.utils.Constansts.Companion.CLICKED_POS
import com.example.arivetsamplechallenge.utils.isNetworkAvailable
import com.example.arivetsamplechallenge.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate)  {
    private val userViewModel:UserViewModel by viewModels()
    lateinit var navController: NavController
    /*private val pagingAdapter by lazy { UserDetailsPagingDataAdapter()}*/
    @Inject
    lateinit var pagingAdapter: UserDetailsPagingDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.user_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

      //  navigateTo(R.id.action_homeFragment_to_userDetailsFragment)

    }
    fun isNetworkAvailable():Boolean=(isNetworkAvailable<MainActivity>())

    fun navigateTo(id: Int,clickPosId:Long) {
        val bundle = bundleOf(CLICKED_POS to clickPosId)
        navController.navigate(id,bundle)
    }

        /* Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
   val action = GameFragmentDirections.actionGameToScore()
   action.score = viewModel.score.value?:0
   NavHostFragment.findNavController(this).navigate(action)*/
}