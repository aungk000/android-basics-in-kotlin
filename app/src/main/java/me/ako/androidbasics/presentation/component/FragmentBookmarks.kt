package me.ako.androidbasics.presentation.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.progressindicator.LinearProgressIndicator
import me.ako.androidbasics.AndroidBasicsApplication
import me.ako.androidbasics.R
import me.ako.androidbasics.data.DataRepository
import me.ako.androidbasics.data.model.BookmarkWithPathway
import me.ako.androidbasics.data.model.UnitWithPathways
import me.ako.androidbasics.databinding.FragmentBookmarksBinding
import me.ako.androidbasics.databinding.FragmentPathwaysBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.presentation.util.BookmarkAdapter
import me.ako.androidbasics.presentation.util.PathwayAdapter

class FragmentBookmarks: Fragment() {
    private val viewModel: AppViewModel by activityViewModels {
        AppViewModel.Factory(
            DataRepository(
                (requireActivity().application as AndroidBasicsApplication).database
            )
        )
    }

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

        binding.apply {


        }

        val adapter = BookmarkAdapter(
            {
                val action = FragmentBookmarksDirections.actionFragmentBookmarksToFragmentActivities(
                    it.pathway.id,
                    it.pathway.number
                )
                findNavController().navigate(action)
            },
            {
                viewModel.deleteBookmark(it.pathway.id)
            }
        )
        binding.recyclerViewBookmarks.adapter = adapter

        viewModel.loadBookmarks().observe(viewLifecycleOwner) {
            progressBar.hide()
            progressBar.setVisibilityAfterHide(View.GONE)
            adapter.submitList(it)
        }
    }
}