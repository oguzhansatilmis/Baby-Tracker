package com.mobilearts.nftworld.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentOnboardingBinding


class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.view.setOnClickListener {
           findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToOnboarding2Fragment())
       }
    }

}