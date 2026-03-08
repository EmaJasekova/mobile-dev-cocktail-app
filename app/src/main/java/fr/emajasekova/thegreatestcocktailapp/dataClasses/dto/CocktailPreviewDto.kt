package fr.emajasekova.thegreatestcocktailapp.dataClasses.dto

import com.google.gson.annotations.SerializedName

data class CocktailPreviewDto(
    @SerializedName("idDrink")       val idCocktail: String?,
    @SerializedName("strDrink")      val strCocktail: String?,
    @SerializedName("strDrinkThumb") val strCocktailThumb: String?,
)
