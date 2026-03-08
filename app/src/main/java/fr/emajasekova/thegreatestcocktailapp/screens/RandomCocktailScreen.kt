package fr.emajasekova.thegreatestcocktailapp.screens

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import fr.emajasekova.thegreatestcocktailapp.dataClasses.Cocktail
import fr.emajasekova.thegreatestcocktailapp.models.AppBarState
import fr.emajasekova.thegreatestcocktailapp.network.ApiClient

@Composable
fun RandomCocktailScreen(modifier: Modifier, onComposing: (AppBarState) -> Unit) {
    val state = loadCocktail(Unit) {
        ApiClient.retrofit.getRandomCocktail().cocktails?.firstOrNull()
    }

    LaunchedEffect(Unit) {
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

    IconButton({
        Toast.makeText(context, "Add to favourites", Toast.LENGTH_LONG).show()
    }) {
        Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Add to favourites"
        )
    }
}
