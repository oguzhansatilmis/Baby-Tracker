package com.mobilearts.nftworld.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentSettBinding


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSettBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView9.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettFragmentToHomeFragment())
        }
    }

}