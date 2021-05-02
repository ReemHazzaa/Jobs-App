package com.reemHazzaa.jobsapp.screens.jobsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.reemHazzaa.jobsapp.R
import com.reemHazzaa.jobsapp.dataSources.remote.NetworkResult
import com.reemHazzaa.jobsapp.databinding.FragmentJobsListBinding
import com.reemHazzaa.jobsapp.screens.MainViewModel
import com.reemHazzaa.jobsapp.screens.jobsList.adapter.JobsAdapter
import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem
import com.reemHazzaa.jobsapp.utils.displayErrorSnackbar
import com.reemHazzaa.jobsapp.utils.makeGone
import com.reemHazzaa.jobsapp.utils.makeVisible
import com.reemHazzaa.jobsapp.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JobsListFragment : Fragment() {

    private var _binding: FragmentJobsListBinding? = null
    private val binding get() = _binding!!
    private val jobsAdapter by lazy { JobsAdapter() }
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobsListBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        setUpRecyclerView()
        readJobs()

        binding.refreshLayout.setOnRefreshListener {
            readJobs()
        }

        return binding.root
    }

    private fun readJobs() {
        lifecycleScope.launch {
            mainViewModel.readJobs.observeOnce(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    activity?.runOnUiThread {
                        jobsAdapter.setList(it[0].jobs as List<JobItem>)
                        hideLoading()
                    }
                } else {
                    requestJobsAPI()
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun requestJobsAPI() {
        mainViewModel.getJobs("api")
        mainViewModel.jobsResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoading()
                    try {
                        response.data.let {
                            @Suppress("UNCHECKED_CAST")
                            jobsAdapter.setList(it as List<JobItem>)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                is NetworkResult.Error -> {
                    hideLoading()
                    readCachedJobsFromDB()
                    displayErrorSnackbar(getString(R.string.no_jobs), requireActivity())
                }
                is NetworkResult.Loading -> showLoading()
            }

        })
    }

    private fun readCachedJobsFromDB() {
        lifecycleScope.launch {
            mainViewModel.readJobs.observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    activity?.runOnUiThread {
                        jobsAdapter.setList(it[0].jobs as List<JobItem>)
                        hideLoading()
                    }
                }
            })
        }
    }

    private fun setUpRecyclerView() {
        binding.jobsRV.apply {
            adapter = jobsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        showLoading()
    }

    private fun showLoading() {
        binding.apply {
            jobsRV.makeGone()
            loading.makeVisible()
        }
    }

    private fun hideLoading() {
        binding.apply {
            jobsRV.makeVisible()
            loading.makeGone()
            refreshLayout.isRefreshing = false
        }
    }

}