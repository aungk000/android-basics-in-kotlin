package me.ako.androidbasics.presentation.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ImageSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import me.ako.androidbasics.AndroidBasicsApplication
import me.ako.androidbasics.R
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
    private lateinit var pathway: PathwayWithActivities

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

        binding.apply {
            lifecycleOwner = viewLifecycleOwner

            btnBookmark.addOnCheckedChangeListener { button, isChecked ->
                button.icon = if (isChecked) {
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark)
                } else {
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark_border)
                }
            }

            val adapter = ActivityAdapter {
                onItemClicked(it)
            }
            recyclerViewActivities.adapter = adapter
        }

        viewModel.loadPathwayWithActivities(args.id).observe(viewLifecycleOwner) {
            pathway = it
            onBind(it)
        }
    }

    private fun onBind(item: PathwayWithActivities) {
        binding.apply {
            this.item = item

            val progress = "${item.pathway.progress}% completed"
            txtProgress.text = progress

            val activityCount = "${item.activities.size} activities"
            txtActivityCount.text = activityCount
        }
    }

    private fun onItemClicked(it: ActivityEntity) {
        if (!it.finished) {
            viewModel.finishActivity(it)

            if (!it.optional) {
                viewModel.updateProgress(pathway.pathway, it.progress)
            }
        }

        if (it.type is ActivityType.Video) {
            // Try to generate a direct intent to the YouTube app
            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if (intent.resolveActivity(requireActivity().packageManager) == null) {
                // YouTube app isn't found, use the web url
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }
            startActivity(intent)
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            startActivity(intent)
        }
    }
}