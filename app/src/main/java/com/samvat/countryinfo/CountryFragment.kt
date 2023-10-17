package com.samvat.countryinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.samvat.countryinfo.adapter.CountryAdapter
import com.samvat.countryinfo.databinding.FragmentCountryBinding
import com.samvat.countryinfo.presentation.CountryViewModel
import com.samvat.countryinfo.utils.Resource

class CountryFragment : Fragment() {

    private val viewModel: CountryViewModel by viewModels {
        CountryViewModel.Factory((activity?.application as CountryInfoApplication).repository)
    }

    private lateinit var binding: FragmentCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCountryBinding.inflate(inflater, container, false)

        val countryAdapter = CountryAdapter()
        binding.countriesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countryAdapter
        }

        viewModel.countries.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    binding.progressBar.isVisible = false

                }

                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is Resource.Success -> {
                    binding.progressBar.isVisible = false

                }
            }
            countryAdapter.submitList(resource.data)
        }
        return binding.root
    }
}