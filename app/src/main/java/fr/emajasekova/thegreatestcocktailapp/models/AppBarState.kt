package fr.emajasekova.thegreatestcocktailapp.models

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

class AppBarState(
    val title: String = "",
    val visible: Boolean = true,
    val actions: (@Composable RowScope.() -> Unit)? = null
)