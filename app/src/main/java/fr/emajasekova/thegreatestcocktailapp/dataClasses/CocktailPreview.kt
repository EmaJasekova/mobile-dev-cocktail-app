package fr.emajasekova.thegreatestcocktailapp.dataClasses

import com.google.gson.annotations.SerializedName

data class CocktailPreview(
    @SerializedName("idDrink")
    val idDrink: String?,

    @SerializedName("strDrink")
    val strDrink: String?,

    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String?
)