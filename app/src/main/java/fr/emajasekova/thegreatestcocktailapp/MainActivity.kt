package fr.emajasekova.thegreatestcocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.emajasekova.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class MainActivity : ComponentActivity() {
    val cosmopolitanIngredients = listOf(
        Ingredient("Citron Vodka", 40.0, IngredientUnit.ML),
        Ingredient("Cointreau (Orange Liqueur)", 15.0, IngredientUnit.ML),
        Ingredient("Fresh Lime Juice", 15.0, IngredientUnit.ML),
        Ingredient("Cranberry Juice", 30.0, IngredientUnit.ML),
        Ingredient("Orange Zest", 2.0, IngredientUnit.GRAM),
        Ingredient("Citron Vodka", 40.0, IngredientUnit.ML),
        Ingredient("Cointreau (Orange Liqueur)", 15.0, IngredientUnit.ML),
        Ingredient("Fresh Lime Juice", 15.0, IngredientUnit.ML),
        Ingredient("Cranberry Juice", 30.0, IngredientUnit.ML),
        Ingredient("Orange Zest", 2.0, IngredientUnit.GRAM)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DetailCocktailScreen(
                        modifier = Modifier.padding(innerPadding),
                        "Cosmopolitan",
                        listOf<Category>(Category.ALCOHOLIC, Category.COLD),
                        GlassType.SMALL,
                        cosmopolitanIngredients
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text("Hello $name!")
        Text("Hello Isen")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheGreatestCocktailAppTheme {
        Greeting("Android")
    }
}