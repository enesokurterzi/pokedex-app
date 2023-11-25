package com.example.pokedexapp.presentation.view.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.databinding.DetailTypeItemBinding
import com.example.pokedexapp.domain.model.PokemonType
import javax.inject.Inject

class DetailTypeAdapter @Inject constructor() :
    RecyclerView.Adapter<DetailTypeAdapter.DetailTypeViewHolder>() {

    class DetailTypeViewHolder(view: DetailTypeItemBinding) :
        RecyclerView.ViewHolder(view.root)

    private val diffUtil = object : DiffUtil.ItemCallback<PokemonType>() {
        override fun areItemsTheSame(oldItem: PokemonType, newItem: PokemonType): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PokemonType, newItem: PokemonType): Boolean {
            return oldItem == newItem
        }

    }

    var types: List<PokemonType>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<DetailTypeItemBinding>(
            inflater,
            R.layout.detail_type_item,
            parent,
            false
        )
        return DetailTypeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return types.size
    }

    override fun onBindViewHolder(holder: DetailTypeViewHolder, position: Int) {
        val binding = DataBindingUtil.bind<DetailTypeItemBinding>(holder.itemView)

        binding?.pokemonType = types[position]
    }

}