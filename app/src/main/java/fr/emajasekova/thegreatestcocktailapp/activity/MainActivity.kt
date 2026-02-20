package fr.emajasekova.thegreatestcocktailapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import fr.emajasekova.thegreatestcocktailapp.models.Category
import fr.emajasekova.thegreatestcocktailapp.models.GlassType
import fr.emajasekova.thegreatestcocktailapp.models.Ingredient
import fr.emajasekova.thegreatestcocktailapp.models.IngredientUnit
import fr.emajasekova.thegreatestcocktailapp.screens.DetailCocktailScreen
import fr.emajasekova.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class MainActivity : ComponentActivity() {
    val cosmopolitanIngredients = listOf(
        Ingredient("Citron Vodka", 40.0, IngredientUnit.ML),
        Ingredient("Cointreau (Orange Liqueur)", 15.0, IngredientUnit.ML),
        Ingredient("Fresh Lime Juice", 15.0, IngredientUnit.ML),
        Ingredient("Cranberry Juice", 30.0, IngredientUnit.ML),
        Ingredient("Orange Zest", 2.0, IngredientUnit.GRAM),
        Ingredient("Citron Vodka", 40.0, IngredientUnit.ML)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize()
                ) { innerPadding ->
                    DetailCocktailScreen(
                        modifier = Modifier.Companion.padding(innerPadding),
                        "Cosmopolitan",
                        listOf<Category>(Category.ALCOHOLIC, Category.COLD),
                        GlassType.SMALL,
                        cosmopolitanIngredients,
                        "Description how to prepare the cocktail very very very very very very long description to span multiple lines to test scrolling"
                    )
                }
            }
        }
    }
}