package fr.emajasekova.thegreatestcocktailapp.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun BottomAppBar(barItems: List<TabBarItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar{
        barItems.forEachIndexed { index, barItem ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    navController.navigate(barItem.title)
                },
                icon = {
                    TabBarIcon(
                        selectedTabIndex == index,
                        barItem.selectedIcon,
                        barItem.unselectedIcon,
                        barItem.title
                    )
                },
                label = { Text(barItem.title) }
            )
        }
    }
}

@Composable
fun TabBarIcon(isSelected: Boolean, selectedIcon: ImageVector, unselectedIcon: ImageVector, title: String) {
    Icon(
        if(isSelected) selectedIcon else unselectedIcon,
        contentDescription = title
    )
}