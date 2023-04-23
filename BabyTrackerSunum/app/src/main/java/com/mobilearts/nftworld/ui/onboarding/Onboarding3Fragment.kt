package com.mobilearts.nftworld.ui.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentOnboarding3Binding


class Onboarding3Fragment : Fragment() {

    private val sharedpref by lazy {
        requireContext().getSharedPreferences("onboardingcontrol", Context.MODE_PRIVATE)
    }

    private lateinit var binding: FragmentOnboarding3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboarding3Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewonboarding.setOnClickListener {
            sharedpref.apply {
                edit().putBoolean("onboardinggecildi",true).apply()
            }
            findNavController().navigate(Onboarding3FragmentDirections.actionOnboarding3FragmentToInapp2Fragment())
        }
    }
}