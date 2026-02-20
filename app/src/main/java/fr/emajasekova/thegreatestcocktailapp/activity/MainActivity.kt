package fr.emajasekova.thegreatestcocktailapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.emajasekova.thegreatestcocktailapp.R
import fr.emajasekova.thegreatestcocktailapp.components.TabBarItem
import fr.emajasekova.thegreatestcocktailapp.models.Category
import fr.emajasekova.thegreatestcocktailapp.screens.navigation.BottomBar
import fr.emajasekova.thegreatestcocktailapp.screens.CategoriesScreen
import fr.emajasekova.thegreatestcocktailapp.screens.navigation.TopBar

import fr.emajasekova.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()

            val randomItem = TabBarItem(
                stringResource(R.string.tab_item_random),
                Icons.Filled.Home,
                Icons.Outlined.Home
            )

            val categoryItem = TabBarItem(
                stringResource(R.string.tab_item_category),
                Icons.Filled.Menu,
                Icons.Outlined.Menu
            )

            val favouriteItem = TabBarItem(
                stringResource(R.string.tab_item_favorite),
                Icons.Filled.Favorite,
                Icons.Outlined.Favorite
            )

            val tabItems = listOf(randomItem, categoryItem, favouriteItem)

            TheGreatestCocktailAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar(context) },
                    bottomBar = { BottomBar(navController, tabItems) }
                ) { innerPadding ->
                    NavHost(navController, startDestination = randomItem.title) {
                        composable(randomItem.title) {

                        }
                        composable(categoryItem.title) {
                            CategoriesScreen(
                                Modifier.padding(innerPadding),
                                listOf(Category.COLD, Category.HOT, Category.ALCOHOLIC, Category.NONALCOHOLIC)
                            )
                        }
                        composable(favouriteItem.title) {

                        }
                    }
                }
            }
        }
    }
}