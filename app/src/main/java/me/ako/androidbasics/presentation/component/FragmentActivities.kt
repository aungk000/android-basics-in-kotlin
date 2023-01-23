package me.ako.androidbasics.presentation.component

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import me.ako.androidbasics.AndroidBasicsApplication
import me.ako.androidbasics.data.DataRepository
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.ActivityType
import me.ako.androidbasics.data.model.PathwayWithActivities
import me.ako.androidbasics.databinding.FragmentActivitiesBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.presentation.util.ActivityAdapter

class FragmentActivities : Fragment() {
    private val viewModel: AppViewModel by activityViewModels {
        AppViewModel.Factory(
            DataRepository(
                (activity?.application as AndroidBasicsApplication).database
            )
        )
    }
    private val args: FragmentPathwaysArgs by navArgs()
    private var _binding: FragmentActivitiesBinding? = null
    private val binding get() = _binding!!

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

        lifecycleScope.launch {
            val item = viewModel.loadPathwayWithActivities(args.id)
            onBind(item)
        }
    }

    private fun onBind(item: PathwayWithActivities) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.item = item

        binding.apply {
            val progress = "${item.pathway.progress}% completed"
            txtProgress.text = progress

            val adapter = ActivityAdapter {
                onItemClicked(item, it)
            }
            recyclerViewActivities.adapter = adapter
        }
    }

    private fun onItemClicked(item: PathwayWithActivities, it: ActivityEntity) {
        if(!it.finished) {
            viewModel.finishActivity(it)
        }

        if(!it.optional) {
            viewModel.updateProgress(item.pathway, it.progress)

            // update ui
            binding.apply {
                val updatedProgress = "${item.pathway.progress}% completed"
                txtProgress.text = updatedProgress
                progressIndicator.progress = item.pathway.progress
            }
        }

        if(it.type is ActivityType.Video) {
            // Try to generate a direct intent to the YouTube app
            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if(intent.resolveActivity(requireActivity().packageManager) == null) {
                // YouTube app isn't found, use the web url
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }
            startActivity(intent)
        }
        else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            startActivity(intent)
        }
    }
}