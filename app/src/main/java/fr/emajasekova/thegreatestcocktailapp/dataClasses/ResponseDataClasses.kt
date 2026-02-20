package fr.emajasekova.thegreatestcocktailapp.dataClasses

import com.google.gson.annotations.SerializedName

data class CocktailResponse(
    @SerializedName("drinks")
    val cocktails: List<Cocktail>?
)

data class CategoryListResponse(
    @SerializedName("drinks")
    val categories: List<Category>?
)

data class DrinkFilterResponse(
    @SerializedName("drinks")
    val cocktailPreviews: List<CocktailPreview>?
)
