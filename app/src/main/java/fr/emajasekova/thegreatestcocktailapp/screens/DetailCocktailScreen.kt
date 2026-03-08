package fr.emajasekova.thegreatestcocktailapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import fr.emajasekova.thegreatestcocktailapp.R
import fr.emajasekova.thegreatestcocktailapp.dataClasses.Cocktail
import fr.emajasekova.thegreatestcocktailapp.dataClasses.Ingredient
import fr.emajasekova.thegreatestcocktailapp.dataClasses.toCocktail
import fr.emajasekova.thegreatestcocktailapp.models.AppBarState
import fr.emajasekova.thegreatestcocktailapp.network.ApiClient

data class CocktailState(val cocktail: Cocktail? = null, val loading: Boolean = true)

@Composable
fun loadCocktail(key: Any, fetch: suspend () -> Cocktail?): CocktailState =
    produceState(initialValue = CocktailState(), key1 = key) {
        value = try {
            CocktailState(cocktail = fetch(), loading = false)
        } catch (e: Exception) {
            CocktailState(loading = false)
        }
    }.value

@Composable
fun DetailCocktailScreen(
    drinkId: String,
    onComposing: (AppBarState) -> Unit,
    modifier: Modifier,
) {
    val state = loadCocktail(drinkId) {
        ApiClient.retrofit.getDetailCocktail(drinkId).cocktails?.firstOrNull()?.toCocktail()
    }

    LaunchedEffect(state.cocktail) {
        state.cocktail?.let {
            onComposing(AppBarState(
                title = it.name,
                actions = { DetailCocktailTopButton(state.cocktail) }
            ))
        }
    }

    CocktailDetailContent(
        cocktail = state.cocktail,
        loading = state.loading,
        modifier = modifier,
    )
}

@Composable
fun CocktailDetailContent(
    cocktail: Cocktail?,
    loading: Boolean,
    modifier: Modifier = Modifier,
) {
    val padding = 20.dp

    Box(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            loading -> CircularProgressIndicator()
            cocktail != null -> Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(Modifier.size(padding))
                DrinkView(cocktail.name, cocktail.thumbnailUrl)

                Spacer(Modifier.size(padding))
                Categories(listOfNotNull(cocktail.category, cocktail.alcoholic))

                Spacer(Modifier.size(12.dp))
                GlassTypeView(cocktail.glass ?: "")
                Spacer(Modifier.size(8.dp))

                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .width(350.dp)
                ) {
                    Spacer(Modifier.size(padding))
                    Ingredients(cocktail.ingredients)

                    Spacer(Modifier.size(padding))
                    Preparation(cocktail.instructions ?: "")
                    Spacer(Modifier.size(padding))
                }
            }
        }
    }
}

@Composable
fun DrinkView(drinkName: String, imageUrl: String?) {
    AsyncImage(
        model = imageUrl,
        contentDescription = drinkName,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.cosmopolitan),
        error = painterResource(id = R.drawable.cosmopolitan),
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
    )
    Spacer(Modifier.height(10.dp))
    Text(
        drinkName,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun GlassTypeView(glassType: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.glass),
            contentDescription = "glass",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Text(
            glassType,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
    }
}

@Composable
fun CategoryChip(label: String, containerColor: Color) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = containerColor,
    ) {
        Text(
            label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.background
        )
    }
}

@Composable
fun Categories(categories: List<String>) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        categories.forEachIndexed { index, category ->
            val chipColor = if (index == 0) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.secondary
            CategoryChip(category, chipColor)
        }
    }
}

@Composable
fun IngredientRow(ingredient: Ingredient) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            ingredient.name,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp
        )
        Text(
            ingredient.measure,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun Ingredients(ingredients: List<Ingredient>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(Modifier.padding(top = 14.dp, bottom = 8.dp)) {
            Text(
                "Ingredients",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
            ingredients.forEach { ingredient ->
                IngredientRow(ingredient)
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }
}

@Composable
fun Preparation(preparation: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                "Instructions",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(10.dp))
            Text(
                preparation,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )
        }
    }
}
