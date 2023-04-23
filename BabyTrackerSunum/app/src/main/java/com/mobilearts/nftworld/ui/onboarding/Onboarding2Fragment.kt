package com.mobilearts.nftworld.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentOnboarding2Binding

class Onboarding2Fragment : Fragment() {

    private lateinit var binding:FragmentOnboarding2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboarding2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.view13.setOnClickListener {
            findNavController().navigate(Onboarding2FragmentDirections.actionOnboarding2FragmentToOnboarding3Fragment())
        }
    }

}