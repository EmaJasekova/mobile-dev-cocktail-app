package fr.emajasekova.thegreatestcocktailapp.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.emajasekova.thegreatestcocktailapp.R
import fr.emajasekova.thegreatestcocktailapp.activity.CocktailsActivity
import fr.emajasekova.thegreatestcocktailapp.dataClasses.Category
import fr.emajasekova.thegreatestcocktailapp.models.AppBarState
import fr.emajasekova.thegreatestcocktailapp.network.ApiClient

data class CategoriesState(val categories: List<Category> = emptyList(), val loading: Boolean = true)

@Composable
fun CategoriesScreen(modifier: Modifier, onComposing: (AppBarState) -> Unit) {
    val context = LocalContext.current

    val state = produceState(initialValue = CategoriesState()) {
        value = try {
            CategoriesState(
                categories = ApiClient.retrofit.getCategories().categories.orEmpty(),
                loading = false
            )
        } catch (e: Exception) {
            CategoriesState(loading = false)
        }
    }.value

    LaunchedEffect(Unit) {
        onComposing(AppBarState(title = "Categories"))
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
        when {
            state.loading -> CircularProgressIndicator()
            state.categories.isEmpty() -> Text(
                text = "No categories found.",
                color = colorResource(R.color.white),
                fontSize = 16.sp
            )
            else -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { }
                items(state.categories) { category ->
                    CategoryCard(
                        name = category.strCategory ?: return@items,
                        onClick = {
                            val intent = Intent(context, CocktailsActivity::class.java)
                            intent.putExtra(CocktailsActivity.CATEGORY, category.strCategory)
                            context.startActivity(intent)
                        }
                    )
                }
                item { }
            }
        }
    }
}

@Composable
private fun CategoryCard(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.purple_700).copy(alpha = 0.6f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .fillMaxWidth(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(R.color.white),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
