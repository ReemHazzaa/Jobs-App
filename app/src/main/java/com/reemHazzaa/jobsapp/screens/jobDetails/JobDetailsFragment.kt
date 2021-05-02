package com.reemHazzaa.jobsapp.screens.jobDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.reemHazzaa.jobsapp.databinding.FragmentJobDetailsBinding
import com.reemHazzaa.jobsapp.screens.MainViewModel
import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem
import com.reemHazzaa.jobsapp.screens.jobsList.data.room.JobsTypeConverter
import com.reemHazzaa.jobsapp.utils.observeOnce
import kotlinx.coroutines.launch

class JobDetailsFragment : Fragment() {

    private var _binding: FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<JobDetailsFragmentArgs>()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobDetailsBinding.inflate(layoutInflater, container, false)
        binding.args = args

        binding.favTB.setOnCheckedChangeListener { buttonView, isChecked ->
            addJobToFav(isChecked)
        }

        return binding.root
    }

    private fun addJobToFav(fav: Boolean) {
        lifecycleScope.launch {
            val jobs = mainViewModel.readJobs.value?.get(0)?.jobs
            jobs?.forEach {
                if (it?.jobId?.equals(args.currentJob.jobId) == true) {
                    it.isFav = fav
                }
            }
            jobs?.let { mainViewModel.updateJobsDB(it) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}