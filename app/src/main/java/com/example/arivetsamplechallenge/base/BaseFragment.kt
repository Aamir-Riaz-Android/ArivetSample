package com.example.arivetsamplechallenge.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <B : ViewBinding>(val bindingFactory: (LayoutInflater) -> B) : Fragment() {
    lateinit var binding: B
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=bindingFactory(layoutInflater)
        return binding.root
      //  return super.onCreateView(inflater, container, savedInstanceState)
    }
}