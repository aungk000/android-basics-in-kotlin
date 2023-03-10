package me.ako.androidbasics.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.progressindicator.LinearProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.UnitWithPathways
import me.ako.androidbasics.databinding.FragmentPathwaysBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.presentation.presenter.PathwayAdapter

@AndroidEntryPoint
class FragmentPathways : Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
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

        val progressBar = requireActivity().findViewById<LinearProgressIndicator>(R.id.progress_main)
        progressBar.show()

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            val adapter = PathwayAdapter {
                val action = FragmentPathwaysDirections.actionFragmentPathwaysToFragmentActivities(
                    id = it.id,
                    number = it.number
                )
                findNavController().navigate(action)
            }
            recyclerViewPathways.adapter = adapter
        }

        viewModel.loadUnitWithPathways(args.id).observe(viewLifecycleOwner) {
            progressBar.hide()
            progressBar.setVisibilityAfterHide(View.GONE)
            onBind(it)
        }
    }

    private fun onBind(item: UnitWithPathways) {
        binding.item = item
    }
}