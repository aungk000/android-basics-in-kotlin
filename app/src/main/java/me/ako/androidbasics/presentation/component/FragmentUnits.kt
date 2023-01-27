package me.ako.androidbasics.presentation.component

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.progressindicator.LinearProgressIndicator
import me.ako.androidbasics.AndroidBasicsApplication
import me.ako.androidbasics.R
import me.ako.androidbasics.data.DataRepository
import me.ako.androidbasics.databinding.FragmentUnitsBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.domain.model.AppViewModel.Status
import me.ako.androidbasics.presentation.util.UnitAdapter

class FragmentUnits : Fragment() {
    private val viewModel: AppViewModel by activityViewModels {
        AppViewModel.Factory(
            DataRepository(
                (requireActivity().application as AndroidBasicsApplication).database
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

        addMenuProvider()

        /*requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // disable onBackPressed
            }
        })*/

        val progressBar =
            requireActivity().findViewById<LinearProgressIndicator>(R.id.progress_main)
        progressBar.show()

        preloadData().observe(viewLifecycleOwner) { status ->
            if (status == Status.Done) {
                val adapter = UnitAdapter(
                    {
                        val action = FragmentUnitsDirections.actionFragmentUnitsToFragmentPathways(
                            it.unit.id
                        )
                        findNavController().navigate(action)
                    },
                    {
                        val action = FragmentUnitsDirections.actionFragmentUnitsToFragmentActivities(
                            it.id,
                            it.number
                        )
                        findNavController().navigate(action)
                    }
                )
                binding.recyclerViewUnits.adapter = adapter

                viewModel.loadUnitsWithPathways().observe(viewLifecycleOwner) {
                    //binding.progressUnits.hide()
                    progressBar.hide()
                    progressBar.setVisibilityAfterHide(View.GONE)
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun preloadData(): LiveData<Status> {
        val loading = MutableLiveData<Status>(Status.Loading)
        val sharedPref = requireActivity().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val preload = sharedPref.getBoolean("preload", true)

        if (preload) {
            viewModel.preloadData().observe(viewLifecycleOwner) {
                if (it == Status.Done) {
                    loading.value = Status.Done
                }
            }

            sharedPref.edit().putBoolean("preload", false).apply()
        } else {
            loading.value = Status.Done
        }

        return loading
    }

    private fun addMenuProvider() {
        val menuHost = requireActivity() as MenuHost
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_bookmark -> {
                        val action =
                            FragmentUnitsDirections.actionFragmentUnitsToFragmentBookmarks()
                        findNavController().navigate(action)
                        true
                    }
                    R.id.menu_settings -> {
                        true
                    }
                    R.id.menu_about -> {
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}