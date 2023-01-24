package me.ako.androidbasics.presentation.component

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ako.androidbasics.AndroidBasicsApplication
import me.ako.androidbasics.data.DataRepository
import me.ako.androidbasics.databinding.FragmentMainBinding
import me.ako.androidbasics.domain.model.AppViewModel
import me.ako.androidbasics.domain.model.AppViewModel.Status
import kotlin.coroutines.CoroutineContext

class FragmentMain : Fragment() {
    private val viewModel: AppViewModel by activityViewModels {
        AppViewModel.Factory(
            DataRepository(
                (activity?.application as AndroidBasicsApplication).database
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMainBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // disable onBackPressed
            }
        })

        val sharedPref = requireActivity().getSharedPreferences("preload", Context.MODE_PRIVATE)
        val preload = sharedPref.getBoolean("preload", false)

        if(!preload) {
            viewModel.preloadData().observe(viewLifecycleOwner) {
                if (it is Status.Done) {
                    val action = FragmentMainDirections.actionFragmentMainToFragmentUnits()
                    findNavController().navigate(action)
                }
            }

            sharedPref.edit().putBoolean("preload", true).apply()
        }
        else {
            val action = FragmentMainDirections.actionFragmentMainToFragmentUnits()
            findNavController().navigate(action)
        }
    }
}