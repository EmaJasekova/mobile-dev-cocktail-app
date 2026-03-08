package fr.emajasekova.thegreatestcocktailapp.screens

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil3.compose.AsyncImage
import fr.emajasekova.thegreatestcocktailapp.R
import fr.emajasekova.thegreatestcocktailapp.activity.DetailCocktailActivity
import fr.emajasekova.thegreatestcocktailapp.managers.FavoriteCocktail
import fr.emajasekova.thegreatestcocktailapp.managers.FavoritesManager
import fr.emajasekova.thegreatestcocktailapp.models.AppBarState

@Composable
fun FavouritesScreen(modifier: Modifier, onComposing: (AppBarState) -> Unit) {
    val context = LocalContext.current
    var favorites by remember { mutableStateOf(FavoritesManager.getFavorites(context)) }

    LaunchedEffect(Unit) {
        onComposing(AppBarState(title = "Favourites"))
    }

    val activity = context as? ComponentActivity
    DisposableEffect(activity) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                favorites = FavoritesManager.getFavorites(context)
            }
        }
        activity?.lifecycle?.addObserver(observer)
        onDispose { activity?.lifecycle?.removeObserver(observer) }
    }

    Box(
        modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.black),
                        colorResource(R.color.purple_700)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        if (favorites.isEmpty()) {
            Text(
                text = "No favourites yet.",
                color = colorResource(R.color.white),
                fontSize = 16.sp
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { }
                items(favorites) { cocktail ->
                    FavoriteCocktailCard(cocktail) {
                        val intent = Intent(context, DetailCocktailActivity::class.java)
                        intent.putExtra(DetailCocktailActivity.DRINKID, cocktail.idDrink)
                        context.startActivity(intent)
                    }
                }
                item { }
            }
        }
    }
}

@Composable
private fun FavoriteCocktailCard(cocktail: FavoriteCocktail, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.purple_700).copy(alpha = 0.6f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = cocktail.strDrinkThumb,
                contentDescription = cocktail.strDrink,
                placeholder = painterResource(R.drawable.cosmopolitan),
                error = painterResource(R.drawable.cosmopolitan),
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Text(
                text = cocktail.strDrink,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(R.color.white)
            )
        }
    }
}
