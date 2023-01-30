package me.ako.androidbasics.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.progressindicator.LinearProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import me.ako.androidbasics.R
import me.ako.androidbasics.databinding.FragmentBookmarksBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.presentation.util.BookmarkAdapter

@AndroidEntryPoint
class FragmentBookmarks: Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
    /*private val viewModel: AppViewModel by activityViewModels {
        AppViewModel.Factory(
            DataRepository(
                (requireActivity().application as AndroidBasicsApplication).database
            )
        )
    }*/

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
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

        val adapter = BookmarkAdapter(
            {
                val action = FragmentBookmarksDirections.actionFragmentBookmarksToFragmentActivities(
                    it.id,
                    it.number
                )
                findNavController().navigate(action)
            },
            {
                it.bookmarked = false
                viewModel.updatePathway(it)
            }
        )
        binding.recyclerViewBookmarks.addItemDecoration(DividerItemDecoration(requireContext(), 1))
        binding.recyclerViewBookmarks.adapter = adapter

        viewModel.loadBookmarks().observe(viewLifecycleOwner) {
            progressBar.hide()
            progressBar.setVisibilityAfterHide(View.GONE)
            adapter.submitList(it)
        }
    }
}