package fr.emajasekova.thegreatestcocktailapp.dataClasses

import com.google.gson.annotations.SerializedName
import fr.emajasekova.thegreatestcocktailapp.dataClasses.dto.CategoryDto
import fr.emajasekova.thegreatestcocktailapp.dataClasses.dto.CocktailDto
import fr.emajasekova.thegreatestcocktailapp.dataClasses.dto.CocktailPreviewDto

data class CocktailResponse(
    @SerializedName("drinks")
    val cocktails: List<CocktailDto>?
)

data class CategoryListResponse(
    @SerializedName("drinks")
    val categories: List<CategoryDto>?
)

data class CocktailFilterResponse(
    @SerializedName("drinks")
    val cocktailPreviews: List<CocktailPreviewDto>?
)
