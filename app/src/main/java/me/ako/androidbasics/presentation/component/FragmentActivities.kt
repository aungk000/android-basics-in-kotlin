package me.ako.androidbasics.presentation.component

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.LinearProgressIndicator
import me.ako.androidbasics.AndroidBasicsApplication
import me.ako.androidbasics.R
import me.ako.androidbasics.data.DataRepository
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.ActivityType
import me.ako.androidbasics.data.model.BookmarkEntity
import me.ako.androidbasics.data.model.PathwayWithActivities
import me.ako.androidbasics.databinding.FragmentActivitiesBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.presentation.util.ActivityAdapter
import org.joda.time.DateTime

class FragmentActivities : Fragment() {
    private val viewModel: AppViewModel by activityViewModels {
        AppViewModel.Factory(
            DataRepository(
                (requireActivity().application as AndroidBasicsApplication).database
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

        /*val progressBar = requireActivity().findViewById<LinearProgressIndicator>(R.id.progress_main)
        progressBar.show()*/

        binding.apply {
            btnBookmark.setOnClickListener {
                /*btn.icon = if (isChecked) {
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark)
                } else {
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark_border)
                }*/
                if(btnBookmark.isChecked) {
                    viewModel.addBookmark(BookmarkEntity(
                        pathwayId = pathway.pathway.id,
                        dateTime = DateTime.now()
                    ))
                    viewModel.updateBookmarked(pathway.pathway, btnBookmark.isChecked)

                    btnBookmark.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark)
                }
                else {
                    viewModel.deleteBookmark(pathway.pathway.id)
                    viewModel.updateBookmarked(pathway.pathway, btnBookmark.isChecked)

                    btnBookmark.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark_border)
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
            /*progressBar.hide()
            progressBar.setVisibilityAfterHide(View.GONE)*/
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

            btnBookmark.icon = if(item.pathway.bookmarked) {
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark)
            } else {
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark_border)
            }
        }
    }

    private fun onItemClicked(it: ActivityEntity) {
        if (!it.finished) {
            viewModel.finishActivity(it, true)

            if (!it.optional) {
                viewModel.updateProgress(pathway.pathway, it.progress)
            }
        }

        intentActionView(it)
    }

    private fun intentActionView(it: ActivityEntity) {
        if (it.type == ActivityType.Video) {
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

    private fun onItemLongClicked(it: ActivityEntity): Boolean {
        return if(it.finished) {
            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle("Mark as unread")
                .setMessage("Are you sure you want to mark ${it.title} activity as unread.")
                .setPositiveButton("Ok") {dialog, which ->
                    viewModel.finishActivity(it, false)

                    if (!it.optional) {
                        viewModel.updateProgress(pathway.pathway, -it.progress)
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