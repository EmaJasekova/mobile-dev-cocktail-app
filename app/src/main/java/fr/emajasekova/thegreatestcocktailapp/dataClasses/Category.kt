package fr.emajasekova.thegreatestcocktailapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("strCategory")
    val strCategory: String?
)
