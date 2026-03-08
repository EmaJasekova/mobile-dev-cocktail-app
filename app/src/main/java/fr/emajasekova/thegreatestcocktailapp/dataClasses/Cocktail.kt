package fr.emajasekova.thegreatestcocktailapp.dataClasses

data class Cocktail(
    val id: String,
    val name: String,
    val category: String?,
    val alcoholic: String?,
    val glass: String?,
    val instructions: String?,
    val thumbnailUrl: String?,
    val ingredients: List<Ingredient>,
)
