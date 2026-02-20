package fr.emajasekova.thegreatestcocktailapp.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fr.emajasekova.thegreatestcocktailapp.activity.CocktailsActivity
import fr.emajasekova.thegreatestcocktailapp.models.Category

@Composable
fun CategoriesScreen(modifier: Modifier, categories: List<Category>) {
    val context = LocalContext.current
    LazyColumn(modifier
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(categories) { category ->
            Card(Modifier.clickable {
                val intent = Intent(context, CocktailsActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("$category",
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth())
            }
        }
    }
}