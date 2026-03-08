package fr.emajasekova.thegreatestcocktailapp.dataClasses

import fr.emajasekova.thegreatestcocktailapp.dataClasses.dto.CategoryDto
import fr.emajasekova.thegreatestcocktailapp.dataClasses.dto.CocktailDto
import fr.emajasekova.thegreatestcocktailapp.dataClasses.dto.CocktailPreviewDto

fun CocktailDto.toCocktail(): Cocktail? {
    val id = idDrink ?: return null
    return Cocktail(
        id = id,
        name = strDrink ?: "",
        category = strCategory,
        alcoholic = strAlcoholic,
        glass = strGlass,
        instructions = strInstructions,
        thumbnailUrl = strDrinkThumb,
        ingredients = toIngredients(),
    )
}

private fun CocktailDto.toIngredients(): List<Ingredient> {
    val names = listOf(
        strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
        strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
        strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
    )
    val measures = listOf(
        strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5,
        strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
        strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15,
    )
    return names.zip(measures)
        .filter { (name, _) -> !name.isNullOrBlank() }
        .map { (name, measure) -> Ingredient(name!!, measure?.trim() ?: "") }
}

fun CocktailPreviewDto.toCocktailPreview(): CocktailPreview? {
    val id = idDrink ?: return null
    return CocktailPreview(
        id = id,
        name = strDrink ?: "",
        thumbnailUrl = strDrinkThumb,
    )
}

fun CategoryDto.toCategory(): Category? {
    val name = strCategory ?: return null
    return Category(name)
}
