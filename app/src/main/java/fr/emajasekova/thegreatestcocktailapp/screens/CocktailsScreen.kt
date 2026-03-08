package fr.emajasekova.thegreatestcocktailapp.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import fr.emajasekova.thegreatestcocktailapp.R
import fr.emajasekova.thegreatestcocktailapp.activity.DetailCocktailActivity
import fr.emajasekova.thegreatestcocktailapp.dataClasses.CocktailFilterResponse
import fr.emajasekova.thegreatestcocktailapp.dataClasses.CocktailPreview
import fr.emajasekova.thegreatestcocktailapp.dataClasses.toCocktailPreview
import fr.emajasekova.thegreatestcocktailapp.network.ApiClient
import retrofit2.Call
import retrofit2.Response

@Composable
fun CocktailsScreen(modifier: Modifier, category: String) {
    val context = LocalContext.current
    var cocktails by remember { mutableStateOf<List<CocktailPreview>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val call = ApiClient.retrofit.getCocktailsPreview(category)
        call.enqueue(object : retrofit2.Callback<CocktailFilterResponse> {
            override fun onResponse(
                call: Call<CocktailFilterResponse?>?,
                response: Response<CocktailFilterResponse?>?
            ) {
                cocktails = response?.body()?.cocktailPreviews
                    ?.mapNotNull { it.toCocktailPreview() }
                    ?: emptyList()
                loading = false
            }

            override fun onFailure(call: Call<CocktailFilterResponse?>?, t: Throwable?) {
                loading = false
            }
        })
    }

    Box(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        when {
            loading -> CircularProgressIndicator()
            cocktails.isEmpty() -> Text(
                text = "No cocktails found.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            else -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(cocktails) { cocktail ->
                    CocktailListCard(cocktail) {
                        val intent = Intent(context, DetailCocktailActivity::class.java)
                        intent.putExtra(DetailCocktailActivity.COCKTAILID, cocktail.id)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
private fun CocktailListCard(cocktail: CocktailPreview, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            AsyncImage(
                model = cocktail.thumbnailUrl,
                contentDescription = cocktail.name,
                placeholder = painterResource(R.drawable.cosmopolitan),
                error = painterResource(R.drawable.cosmopolitan),
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
            Text(
                text = cocktail.name,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "›",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Light
            )
        }
    }
}
