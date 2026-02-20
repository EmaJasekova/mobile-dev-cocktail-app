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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {

            }
        }
    }
}