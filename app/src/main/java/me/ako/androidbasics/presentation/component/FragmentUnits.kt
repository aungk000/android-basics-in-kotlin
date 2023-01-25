package me.ako.androidbasics.presentation.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.Visibility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ako.androidbasics.AndroidBasicsApplication
import me.ako.androidbasics.data.DataRepository
import me.ako.androidbasics.databinding.FragmentUnitsBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.presentation.util.UnitAdapter

class FragmentUnits : Fragment() {
    private val viewModel: AppViewModel by activityViewModels {
        AppViewModel.Factory(
            DataRepository(
                (activity?.application as AndroidBasicsApplication).database
            )
        )
    }
    private var _binding: FragmentUnitsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnitsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UnitAdapter {
            val action = FragmentUnitsDirections.actionFragmentUnitsToFragmentPathways(
                it.unit.id
            )
            findNavController().navigate(action)
        }
        binding.recyclerViewUnits.adapter = adapter

        viewModel.loadUnitsWithPathways().observe(viewLifecycleOwner) {
            binding.progressUnits.hide()
            //binding.progressUnits.setVisibilityAfterHide(View.GONE)
            adapter.submitList(it)
        }
    }
}