package com.reemHazzaa.jobsapp.screens.jobDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.navArgs
import com.reemHazzaa.jobsapp.databinding.FragmentJobDetailsBinding

class JobDetailsFragment : Fragment() {

    private var _binding: FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<JobDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobDetailsBinding.inflate(layoutInflater, container, false)
        binding.args = args
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}