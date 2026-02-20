package fr.emajasekova.thegreatestcocktailapp.screens.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TopBar(context: Context) {
    TopAppBar({
        Text("Detail")
    }, actions = {
        IconButton({
            Toast
                .makeText(context, "Add to favourite", Toast.LENGTH_LONG)
                .show()
        }) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "Add to favourites"
            )
        }
    })
}