package fr.emajasekova.thegreatestcocktailapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import fr.emajasekova.thegreatestcocktailapp.screens.CocktailsScreen
import fr.emajasekova.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class CocktailsActivity : ComponentActivity() {

    companion object {
        const val CATEGORY = "CATEGORY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val category = intent.getStringExtra(CATEGORY) ?: ""

        setContent {
            TheGreatestCocktailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CocktailsScreen(Modifier.padding(innerPadding), category)
                }
            }
        }
    }
}