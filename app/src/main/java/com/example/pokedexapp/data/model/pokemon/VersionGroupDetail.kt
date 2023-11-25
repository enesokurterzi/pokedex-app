package com.example.pokedexapp.data.model.pokemon


import com.google.gson.annotations.SerializedName

data class VersionGroupDetail(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int,
    @SerializedName("move_learn_method")
    val moveLearnMethod: com.example.pokedexapp.data.model.pokemon.MoveLearnMethod,
    @SerializedName("version_group")
    val versionGroup: com.example.pokedexapp.data.model.pokemon.VersionGroup
)