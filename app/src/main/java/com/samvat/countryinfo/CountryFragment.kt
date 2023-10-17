package com.samvat.countryinfo

import CountryAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.recyclerview.widget.LinearLayoutManager
import com.samvat.countryinfo.databinding.FragmentCountryBinding
import com.samvat.countryinfo.presentation.CountryViewModel
import com.samvat.countryinfo.utils.Resource

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CountryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountryFragment : Fragment() {

    private val viewModel: CountryViewModel by viewModels {
        CountryViewModel.Factory((activity?.application as CountryInfoApplication).repository)
    }

    private lateinit var binding : FragmentCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCountryBinding.inflate(inflater,container,false)

        val countryAdapter = CountryAdapter()
        binding.countriesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countryAdapter
        }

        viewModel.countries.observe(viewLifecycleOwner) { resource ->
            when(resource){
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




        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            countries?.let {
                Log.i("Am here", countries.data.toString())
            }
        }
        return binding.root
    }
}