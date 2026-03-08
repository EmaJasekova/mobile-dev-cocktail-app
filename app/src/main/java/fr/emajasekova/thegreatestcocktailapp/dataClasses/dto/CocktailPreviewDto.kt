package fr.emajasekova.thegreatestcocktailapp.dataClasses.dto

import com.google.gson.annotations.SerializedName

data class CocktailPreviewDto(
    @SerializedName("idDrink")       val idDrink: String?,
    @SerializedName("strDrink")      val strDrink: String?,
    @SerializedName("strDrinkThumb") val strDrinkThumb: String?,
)
