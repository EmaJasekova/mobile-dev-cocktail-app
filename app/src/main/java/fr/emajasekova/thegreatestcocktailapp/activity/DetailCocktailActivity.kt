package fr.emajasekova.thegreatestcocktailapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import fr.emajasekova.thegreatestcocktailapp.models.AppBarState
import fr.emajasekova.thegreatestcocktailapp.screens.DetailCocktailScreen
import fr.emajasekova.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class DetailCocktailActivity : ComponentActivity() {

    companion object {
        const val COCKTAILID = "COCKTAIL_ID"
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val cocktailId = intent.getStringExtra(COCKTAILID) ?: ""

        setContent {
            val appBarState = remember { mutableStateOf(AppBarState()) }

            TheGreatestCocktailAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(appBarState.value.title) },
                            actions = { appBarState.value.actions?.invoke(this) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                titleContentColor = MaterialTheme.colorScheme.onSurface,
                                actionIconContentColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    DetailCocktailScreen(
                        cocktailId = cocktailId,
                        onComposing = { appBarState.value = it },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
