package fr.emajasekova.thegreatestcocktailapp.managers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.emajasekova.thegreatestcocktailapp.dataClasses.Cocktail

data class FavoriteCocktail(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String?
)

object FavoritesManager {

    private const val PREFS_NAME = "favorites_prefs"
    private const val KEY_FAVORITES = "favorites"
    private val gson = Gson()

    fun getFavorites(context: Context): List<FavoriteCocktail> {
        val json = prefs(context).getString(KEY_FAVORITES, null) ?: return emptyList()
        val type = object : TypeToken<List<FavoriteCocktail>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    fun isFavorite(context: Context, drinkId: String): Boolean =
        getFavorites(context).any { it.idDrink == drinkId }

    fun toggleFavorite(context: Context, cocktail: Cocktail) {
        val id = cocktail.idCocktail ?: return
        val favorites = getFavorites(context).toMutableList()
        val index = favorites.indexOfFirst { it.idDrink == id }
        if (index >= 0) {
            favorites.removeAt(index)
        } else {
            favorites.add(
                FavoriteCocktail(
                    idDrink = id,
                    strDrink = cocktail.strCocktail ?: "",
                    strDrinkThumb = cocktail.strDrinkThumb
                )
            )
        }
        prefs(context).edit().putString(KEY_FAVORITES, gson.toJson(favorites)).apply()
    }

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}
