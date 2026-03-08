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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.ui.unit.sp
import fr.emajasekova.thegreatestcocktailapp.R
import fr.emajasekova.thegreatestcocktailapp.dataClasses.Cocktail
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
        ApiClient.retrofit.getDetailCocktail(drinkId).cocktails?.firstOrNull()
    }

    LaunchedEffect(state.cocktail) {
        state.cocktail?.let { onComposing(AppBarState(title = it.strCocktail ?: "")) }
    }

    CocktailDetailContent(
        cocktail = state.cocktail,
        loading = state.loading,
        modifier = modifier,
        overlayContent = { TopIcons() }
    )
}

@Composable
fun CocktailDetailContent(
    cocktail: Cocktail?,
    loading: Boolean,
    modifier: Modifier = Modifier,
    overlayContent: @Composable () -> Unit = {},
) {
    val padding = 20.dp

    Box(
        modifier
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.black),
                        colorResource(R.color.purple_700)
                    )
                )
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        overlayContent()

        when {
            loading -> CircularProgressIndicator()
            cocktail != null -> Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(Modifier.size(padding))
                DrinkView(cocktail.strCocktail ?: "", cocktail.strDrinkThumb)

                Spacer(Modifier.size(padding))
                Categories(listOfNotNull(cocktail.strCategory, cocktail.strAlcoholic))

                Spacer(Modifier.size(padding))
                GlassTypeView(cocktail.strGlass ?: "")
                Spacer(Modifier.size(8.dp))

                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .width(350.dp)
                ) {
                    Spacer(Modifier.size(padding))
                    Ingredients(cocktail.ingredientList())

                    Spacer(Modifier.size(padding))
                    Preparation(cocktail.strInstructions ?: "")
                    Spacer(Modifier.size(padding))
                }
            }
        }
    }
}

@Composable
fun TopIcons() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        ButtonWithIcon(R.drawable.reload, "reload", {})
        ButtonWithIcon(R.drawable.heart, "heart", {})
    }
}

@Composable
fun ButtonWithIcon(iconId: Int, iconDescription: String, onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = iconDescription,
            modifier = Modifier
                .width(25.dp)
                .height(25.dp)
        )
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
            .width(150.dp)
            .height(150.dp)
            .clip(CircleShape)
            .border(2.dp, colorResource(R.color.white), CircleShape)
    )
    Text(
        drinkName,
        fontSize = 28.sp,
        color = colorResource(R.color.white)
    )
}

@Composable
fun GlassTypeView(glassType: String) {
    Row {
        Icon(
            painter = painterResource(R.drawable.glass),
            contentDescription = "glass",
            modifier = Modifier
                .width(18.dp)
                .height(18.dp)
        )
        Text(glassType)
    }
}

@Composable
fun CategoryView(category: String) {
    Box(
        Modifier
            .clip(CircleShape)
            .border(2.dp, colorResource(R.color.white), CircleShape)
            .background(
                Brush.horizontalGradient(
                    listOf(
                        colorResource(R.color.teal_200),
                        colorResource(R.color.teal_700)
                    )
                )
            )
    ) {
        Text(
            category,
            fontSize = 20.sp,
            color = colorResource(R.color.white)
        )
    }
}

@Composable
fun Categories(categories: List<String>) {
    Row {
        categories.forEach { category -> CategoryView(category) }
    }
}

@Composable
fun IngredientView(ingredient: Pair<String, String>) {
    Row {
        Text(ingredient.first)
        Text(ingredient.second)
    }
}

@Composable
fun Ingredients(ingredients: List<Pair<String, String>>) {
    val padding = 10.dp
    Column {
        Card(Modifier.fillMaxWidth()) {
            Text(
                text = "Ingredients",
                modifier = Modifier.padding(15.dp),
            )
            ingredients.forEach { ingredient ->
                IngredientView(ingredient)
                Spacer(Modifier.size(padding))
            }
        }
    }
}

@Composable
fun Preparation(preparation: String) {
    Column {
        Card(Modifier.fillMaxWidth()) {
            Text(
                text = preparation,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}
