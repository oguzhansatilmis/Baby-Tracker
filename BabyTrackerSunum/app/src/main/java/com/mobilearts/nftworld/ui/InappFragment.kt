package com.mobilearts.nftworld.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.databinding.FragmentInapp2Binding
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.getOfferingsWith
import com.revenuecat.purchases.purchasePackageWith


class InappFragment() : Fragment() {
    private lateinit var binding: FragmentInapp2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentInapp2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inappnextButton.setOnClickListener {
            findNavController().navigate(InappFragmentDirections.actionInapp2FragmentToLoginFragment())
        }
        fun setButtonSelected(view: View) {
            binding.weekly.setBackgroundResource(R.drawable.inappbutton)
            binding.monthly.setBackgroundResource(R.drawable.inappbutton)
            binding.annual.setBackgroundResource(R.drawable.inappbutton)
            view.setBackgroundResource(R.drawable.select_preminum)
        }

        binding.weekly.setOnClickListener { setButtonSelected(binding.weekly) }
        binding.monthly.setOnClickListener { setButtonSelected(binding.monthly) }
        binding.annual.setOnClickListener { setButtonSelected(binding.annual) }

        binding.inappnextButton.setOnClickListener {
         findNavController().navigate(InappFragmentDirections.actionInapp2FragmentToLoginFragment())
        }
    }
}