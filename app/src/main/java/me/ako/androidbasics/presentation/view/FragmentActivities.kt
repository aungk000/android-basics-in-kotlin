package me.ako.androidbasics.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.progressindicator.LinearProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.PathwayWithActivities
import me.ako.androidbasics.databinding.FragmentActivitiesBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.presentation.presenter.ActivityAdapter
import me.ako.androidbasics.presentation.util.Utils
import javax.inject.Inject

@AndroidEntryPoint
class FragmentActivities : Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
    private val args: FragmentPathwaysArgs by navArgs()
    private var _binding: FragmentActivitiesBinding? = null
    private val binding get() = _binding!!
    private lateinit var pathway: PathwayWithActivities
    @Inject lateinit var utils: Utils

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivitiesBinding.inflate(inflater, container, false)
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
            btnBookmark.setOnClickListener {
                val checked = btnBookmark.isChecked
                if(checked) {
                    pathway.pathway.bookmarked = checked
                    viewModel.updatePathway(pathway.pathway)
                } else {
                    pathway.pathway.bookmarked = checked
                    viewModel.updatePathway(pathway.pathway)
                }
            }

            val adapter = ActivityAdapter(
                {
                    onItemClicked(it)
                },
                {
                    onItemLongClicked(it)
                }
            )
            recyclerViewActivities.adapter = adapter
        }

        viewModel.loadPathwayWithActivities(args.id).observe(viewLifecycleOwner) {
            progressBar.hide()
            progressBar.setVisibilityAfterHide(View.GONE)
            pathway = it
            onBind(it)
        }
    }

    private fun onBind(item: PathwayWithActivities) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            this.item = item

            val activityCount = "${item.activities.size} activities"
            txtActivityCount.text = activityCount
        }
    }

    private fun onItemClicked(it: ActivityEntity) {
        if (!it.finished) {
            it.finished = true
            viewModel.updateActivity(it)

            if (!it.optional) {
                pathway.pathway.progress += it.progress
                viewModel.updatePathway(pathway.pathway)
            }
        }

        utils.handleActivityClick(requireActivity(), it)
    }

    private fun onItemLongClicked(it: ActivityEntity): Boolean {
        return if(it.finished) {
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Mark as unread")
                .setMessage("Are you sure you want to mark ${it.title} activity as unread.")
                .setPositiveButton("Ok") {dialog, which ->
                    it.finished = false
                    viewModel.updateActivity(it)

                    if (!it.optional) {
                        pathway.pathway.progress -= it.progress
                        viewModel.updatePathway(pathway.pathway)
                    }
                }
                .setNegativeButton("Cancel") {dialog, which -> }
                .setCancelable(true)
            dialog.show()
            true
        }
        else {
            false
        }
    }
}