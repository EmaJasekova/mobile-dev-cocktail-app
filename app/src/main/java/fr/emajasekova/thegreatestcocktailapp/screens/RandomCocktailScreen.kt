package fr.emajasekova.thegreatestcocktailapp.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import fr.emajasekova.thegreatestcocktailapp.dataClasses.Cocktail
import fr.emajasekova.thegreatestcocktailapp.managers.FavoritesManager
import fr.emajasekova.thegreatestcocktailapp.models.AppBarState
import fr.emajasekova.thegreatestcocktailapp.network.ApiClient

@Composable
fun RandomCocktailScreen(modifier: Modifier, onComposing: (AppBarState) -> Unit) {
    val state = loadCocktail(Unit) {
        ApiClient.retrofit.getRandomCocktail().cocktails?.firstOrNull()
    }

    LaunchedEffect(state.cocktail) {
        onComposing(
            AppBarState(
                title = "Random Cocktail",
                actions = { DetailCocktailTopButton(state.cocktail) }
            )
        )
    }

    CocktailDetailContent(
        cocktail = state.cocktail,
        loading = state.loading,
        modifier = modifier
    )
}

@Composable
fun DetailCocktailTopButton(cocktail: Cocktail?) {
    val context = LocalContext.current
    var isFavorite by remember(cocktail?.idCocktail) {
        mutableStateOf(
            cocktail?.idCocktail?.let { FavoritesManager.isFavorite(context, it) } ?: false
        )
    }

    IconButton(
        onClick = {
            cocktail?.let {
                FavoritesManager.toggleFavorite(context, it)
                isFavorite = !isFavorite
            }
        },
        enabled = cocktail != null
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = if (isFavorite) "Remove from favourites" else "Add to favourites"
        )
    }
}
