package fr.emajasekova.thegreatestcocktailapp.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import fr.emajasekova.thegreatestcocktailapp.components.BottomAppBar
import fr.emajasekova.thegreatestcocktailapp.components.TabBarItem

@Composable
fun BottomBar(navController: NavController, tabItems: List<TabBarItem>) {
    BottomAppBar(
        tabItems,
        navController
    )
}
