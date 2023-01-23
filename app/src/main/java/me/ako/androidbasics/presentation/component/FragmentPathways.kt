package me.ako.androidbasics.presentation.component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import me.ako.androidbasics.AndroidBasicsApplication
import me.ako.androidbasics.data.DataRepository
import me.ako.androidbasics.data.model.UnitWithPathways
import me.ako.androidbasics.databinding.FragmentPathwaysBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.presentation.util.PathwayAdapter

class FragmentPathways : Fragment() {
    private val viewModel: AppViewModel by activityViewModels {
        AppViewModel.Factory(
            DataRepository(
                (activity?.application as AndroidBasicsApplication).database
            )
        )
    }
    private val args: FragmentPathwaysArgs by navArgs()
    private var _binding: FragmentPathwaysBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPathwaysBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val item = viewModel.loadUnitWithPathways(args.id)
            onBind(item)
        }
    }

    private fun onBind(item: UnitWithPathways) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            this.item = item

            val unit = "Unit ${item.unit.id}: ${item.unit.title}"
            txtTitle.text = unit

            val adapter = PathwayAdapter {
                val action = FragmentPathwaysDirections.actionFragmentPathwaysToFragmentActivities(
                    it.id
                )
                findNavController().navigate(action)
            }
            recyclerViewPathways.adapter = adapter
        }
    }
}