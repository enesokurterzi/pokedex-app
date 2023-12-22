package com.example.pokedexapp.presentation.view.detail

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexapp.R
import com.example.pokedexapp.databinding.FragmentDetailBinding
import javax.inject.Inject

class DetailFragment @Inject constructor(
    private val detailTypeAdapter: DetailTypeAdapter
) : Fragment(R.layout.fragment_detail) {

    constructor() : this(DetailTypeAdapter())

    private var fragmentDetailBinding: FragmentDetailBinding? = null
    private lateinit var detailViewModel: DetailViewModel
    private var startX = 0f
    private var lastOffsetX = 0f


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]

        val binding = FragmentDetailBinding.bind(view)
        fragmentDetailBinding = binding

        val bundle: DetailFragmentArgs by navArgs()
        val pokemonListPosition = bundle.pokemonPosition
        val detailPokemonList = bundle.pokemonList
        val pokemonId = detailPokemonList.pokemonList[pokemonListPosition.toInt()].id

        detailViewModel.loadData(pokemonId)

        onClickSetter(pokemonListPosition, detailPokemonList)

        onBackClickSetter()

        subscribeToObservers()

        binding.detailTypeRecyclerView.apply {
            adapter = detailTypeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        listenImageSwipe(pokemonListPosition, detailPokemonList)
    }

    override fun onResume() {
        fragmentDetailBinding?.pokemonDetail?.copy(
            color = colorTransform(R.color.mediumColor)
        ).also { fragmentDetailBinding?.pokemonDetail = it }

        super.onResume()
    }

    override fun onDestroyView() {
        fragmentDetailBinding = null
        super.onDestroyView()
    }

    private fun subscribeToObservers() {
        detailViewModel.pokemonDetail.observe(viewLifecycleOwner) {
            detailTypeAdapter.types = it?.types?.map { pokemonType ->
                pokemonType.copy(
                    typeColor = colorTransform(pokemonType.typeColor)
                )
            } ?: listOf()
            fragmentDetailBinding?.pokemonDetail = it?.copy(
                color = colorTransform(it.color),
                colorTransparent = ColorUtils.setAlphaComponent(
                    colorTransform(it.color), 64
                )
            )
            if (it != null) {
                requireActivity().window.statusBarColor = colorTransform(it.color)
            }
        }

        detailViewModel.pokemonAbout.observe(viewLifecycleOwner) {
            fragmentDetailBinding?.pokemonAbout = it
        }

        detailViewModel.isLoading.observe(viewLifecycleOwner) {
            if (!it) {
                fragmentDetailBinding?.apply {
                    pokemonDetailLinearLayout.visibility = View.VISIBLE
                    detailLoadingLinearLayout.visibility = View.GONE
                }
            } else {
                parentFragment?.view?.setBackgroundColor(colorTransform(R.color.mediumColor))
                requireActivity().window.statusBarColor = colorTransform(R.color.mediumColor)
                fragmentDetailBinding?.apply {
                    pokemonDetailMasterLinearLayout.background =
                        ColorDrawable(colorTransform(R.color.mediumColor))
                    pokemonDetailLinearLayout.visibility = View.GONE
                    detailLoadingLinearLayout.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun colorTransform(color: Int): Int {
        return ContextCompat.getColor(requireContext(), color)
    }

    private fun onClickSetter(
        pokemonListPosition: String,
        detailPokemonList: DetailPokemonListData
    ) {
        setButtonVisibility(pokemonListPosition, detailPokemonList)
        fragmentDetailBinding?.apply {

            detailToLeftImageView.setOnClickListener {
                navigateToSelf((pokemonListPosition.toInt() - 1).toString(), detailPokemonList)
            }

            detailToRightImageView.setOnClickListener {
                navigateToSelf((pokemonListPosition.toInt() + 1).toString(), detailPokemonList)
            }

        }
    }

    private fun setButtonVisibility(
        pokemonListPosition: String,
        detailPokemonList: DetailPokemonListData
    ) {
        if (pokemonListPosition.toInt() == 0) {
            fragmentDetailBinding?.detailToLeftImageView?.visibility = View.INVISIBLE
        }
        if (pokemonListPosition.toInt() == detailPokemonList.pokemonList.size - 1) {
            fragmentDetailBinding?.detailToRightImageView?.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun listenImageSwipe(
        pokemonListPosition: String,
        detailPokemonList: DetailPokemonListData
    ) {
        fragmentDetailBinding?.PokemonDetailImage?.setOnTouchListener { _, event ->
            handleTouch(event, pokemonListPosition, detailPokemonList)
        }
    }

    private fun handleTouch(
        event: MotionEvent,
        pokemonListPosition: String,
        detailPokemonList: DetailPokemonListData
    ): Boolean {
        var offsetX = 0f

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.rawX
            }

            MotionEvent.ACTION_MOVE -> {
                offsetX = event.rawX - startX
                moveImageView(offsetX)
                lastOffsetX = offsetX
            }

            MotionEvent.ACTION_UP -> {
                centerImageView()

                swipeToGo(lastOffsetX, pokemonListPosition, detailPokemonList)
            }
        }
        return true
    }

    private fun moveImageView(offsetX: Float) {
        fragmentDetailBinding?.PokemonDetailImage?.translationX = offsetX
    }

    private fun centerImageView() {
        fragmentDetailBinding?.PokemonDetailImage?.animate()
            ?.translationX(0f)
            ?.translationY(0f)
            ?.setDuration(500)
            ?.start()
    }

    private fun swipeToGo(
        offsetX: Float,
        pokemonListPosition: String,
        detailPokemonList: DetailPokemonListData
    ) {
        if (offsetX > 200) {
            if (pokemonListPosition.toInt() == 0) {
                Toast.makeText(
                    requireContext(),
                    "There is nothing to see there.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                navigateToSelf((pokemonListPosition.toInt() - 1).toString(), detailPokemonList)
            }

        } else if (offsetX < -200) {
            if (pokemonListPosition.toInt() == detailPokemonList.pokemonList.size - 1) {
                Toast.makeText(
                    requireContext(),
                    "There is nothing to see there.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                navigateToSelf((pokemonListPosition.toInt() + 1).toString(), detailPokemonList)
            }

        }

    }

    private fun navigateToSelf(position: String, detailPokemonList: DetailPokemonListData) {
        val action = DetailFragmentDirections.actionDetailFragmentSelf(
            position,
            detailPokemonList
        )
        Navigation.findNavController(requireView()).navigate(action)

    }

    private fun onBackClickSetter() {
        fragmentDetailBinding?.detailOnBackImageView?.setOnClickListener {
            popBackToMain()
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            popBackToMain()
        }
    }

    private fun popBackToMain() {
        findNavController().popBackStack(R.id.homeFragment, false)
    }


}