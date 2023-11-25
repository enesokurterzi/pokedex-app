package com.example.pokedexapp.presentation.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.databinding.PokemonListRecyclerViewItemBinding
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.presentation.view.detail.DetailPokemonListData
import javax.inject.Inject

class HomeListAdapter @Inject constructor() :
    RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {

    class HomeListViewHolder(view: PokemonListRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(view.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return false
        }

    }

    var pokemons: List<Pokemon>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<PokemonListRecyclerViewItemBinding>(
            inflater,
            R.layout.pokemon_list_recycler_view_item,
            parent,
            false
        )
        return HomeListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        val binding = DataBindingUtil.bind<PokemonListRecyclerViewItemBinding>(holder.itemView)

        holder.itemView.setOnClickListener {
            val detailPokemonList = DetailPokemonListData(pokemons)
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                position.toString(),
                detailPokemonList
            )
            Navigation.findNavController(it).navigate(action)
        }

        var arrangedPokemon = pokemons[position]
        arrangedPokemon = arrangedPokemon.copy(
            id = "#" + arrangedPokemon.id.padStart(3, '0'),
            name = arrangedPokemon.name.replaceFirstChar { it.uppercaseChar() }
        )

        binding?.pokemon = arrangedPokemon
    }

}