package com.example.pokedexapp.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment @Inject constructor(
    val homeListAdapter: HomeListAdapter
) : Fragment(R.layout.fragment_home) {

    constructor() : this(HomeListAdapter())

    private var fragmentHomeBinding: FragmentHomeBinding? = null
    lateinit var homeViewModel: HomeViewModel
    private var popupWindow: PopupWindow? = null
    private var searchText = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        val binding = FragmentHomeBinding.bind(view)
        fragmentHomeBinding = binding

        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.PrimaryColor)

        subscribeToObservers()

        binding.pokemonListRecyclerView.apply {
            adapter = homeListAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        setupPagination()

        searchQueryControl()

        homeSortMenu()

        setSortMenuButtonSrc()
    }


    override fun onDestroyView() {
        fragmentHomeBinding = null
        super.onDestroyView()
    }

    private fun subscribeToObservers() {
        homeViewModel.shownList.observe(viewLifecycleOwner) {
            homeListAdapter.pokemons = it ?: listOf()
        }
    }

    private fun setupPagination() {
        fragmentHomeBinding?.pokemonListRecyclerView?.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val visibleItemCount = layoutManager?.childCount ?: 0
                val totalItemCount = layoutManager?.itemCount ?: 0
                val firstVisibleItemPosition = layoutManager?.findFirstVisibleItemPosition() ?: 0

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    homeViewModel.loadShownList()
                }

            }
        })
    }

    private fun searchQueryControl() {
        var job: Job? = null

        fragmentHomeBinding?.homePokemonSearch?.setOnQueryTextListener(object :
            OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                job?.cancel()
                job = lifecycleScope.launch {
                    delay(200)
                    scrollTopRecyclerView()
                    searchPokemon(query)
                    fragmentHomeBinding?.homePokemonSearch?.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (searchText != newText) {
                    if (newText != null) {
                        searchText = newText
                    }
                    job?.cancel()
                    job = lifecycleScope.launch {
                        delay(200)
                        scrollTopRecyclerView()
                        searchPokemon(newText)
                    }
                }

                return true
            }
        })
    }

    private fun searchPokemon(searchText: String?) {
        if (searchText != null) {
            homeViewModel.searchPokemon(searchText.lowercase())
        }
    }

    private fun homeSortMenu() {
        fragmentHomeBinding?.homeSortMenuButton?.setOnClickListener {
            showPopupMenu(it)
        }
    }

    private fun showPopupMenu(view: View) {
        val inflater = LayoutInflater.from(requireContext())
        val popupView: View = inflater.inflate(R.layout.custom_menu, null)

        popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )

        popupWindow?.isOutsideTouchable = true
        popupWindow?.isFocusable = true

        val locationInWindow = IntArray(2)
        view.getLocationOnScreen(locationInWindow)

        popupWindow?.showAtLocation(
            view,
            0,
            locationInWindow[0] - 200,
            locationInWindow[1] + view.bottom
        )

        listenSortEvent(popupView)
    }

    private fun listenSortEvent(view: View) {
        val radioGroup = view.findViewById<RadioGroup>(R.id.sortRadioGroup)

        setRadioButton(view)

        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.numberRadioButton -> {
                    scrollTopRecyclerView()
                    homeViewModel.apply {
                        sortEvent.value = SortEvent.ByID
                        sortShownList()
                    }
                }

                R.id.nameRadioButton -> {
                    scrollTopRecyclerView()
                    homeViewModel.apply {
                        sortEvent.value = SortEvent.ByName
                        sortShownList()
                    }
                }
            }
            setSortMenuButtonSrc()
        }
    }

    private fun setRadioButton(view: View) {
        when (homeViewModel.sortEvent.value) {
            is SortEvent.ByName -> {
                view.findViewById<RadioButton>(R.id.nameRadioButton).performClick()
            }

            is SortEvent.ByID -> {
                view.findViewById<RadioButton>(R.id.numberRadioButton).performClick()
            }

            else -> {
                view.findViewById<RadioButton>(R.id.numberRadioButton).performClick()
            }
        }

    }

    private fun setSortMenuButtonSrc() {
        fragmentHomeBinding?.homeSortMenuButton?.setImageResource(
            when (homeViewModel.sortEvent.value) {
                is SortEvent.ByName -> R.drawable.text_format

                is SortEvent.ByID -> R.drawable.tag

                else -> R.drawable.tag
            }
        )

    }

    private fun scrollTopRecyclerView() {
        lifecycleScope.launch {
            delay(200)
            fragmentHomeBinding?.pokemonListRecyclerView?.smoothScrollToPosition(0)
        }

    }

}