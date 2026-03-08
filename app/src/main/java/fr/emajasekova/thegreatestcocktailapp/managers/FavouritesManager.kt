package fr.emajasekova.thegreatestcocktailapp.managers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.emajasekova.thegreatestcocktailapp.dataClasses.Cocktail

data class FavouriteCocktail(
    val idCocktail: String,
    val strCocktail: String,
    val strCocktailThumb: String?
)

object FavouritesManager {

    private const val PREFS_NAME = "favourites_prefs"
    private const val KEY_FAVOURITES = "favourites"
    private val gson = Gson()

    fun getFavourites(context: Context): List<FavouriteCocktail> {
        val json = prefs(context).getString(KEY_FAVOURITES, null) ?: return emptyList()
        val type = object : TypeToken<List<FavouriteCocktail>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    fun isFavourite(context: Context, cocktailId: String): Boolean =
        getFavourites(context).any { it.idCocktail == cocktailId }

    fun toggleFavourite(context: Context, cocktail: Cocktail) {
        val favourites = getFavourites(context).toMutableList()
        val index = favourites.indexOfFirst { it.idCocktail == cocktail.id }
        if (index >= 0) {
            favourites.removeAt(index)
        } else {
            favourites.add(
                FavouriteCocktail(
                    idCocktail = cocktail.id,
                    strCocktail = cocktail.name,
                    strCocktailThumb = cocktail.thumbnailUrl
                )
            )
        }
        prefs(context).edit().putString(KEY_FAVOURITES, gson.toJson(favourites)).apply()
    }

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}
