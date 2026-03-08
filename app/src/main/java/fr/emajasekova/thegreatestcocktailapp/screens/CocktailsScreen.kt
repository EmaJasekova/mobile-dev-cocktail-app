package fr.emajasekova.thegreatestcocktailapp.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import fr.emajasekova.thegreatestcocktailapp.activity.DetailCocktailActivity
import fr.emajasekova.thegreatestcocktailapp.dataClasses.CocktailFilterResponse
import fr.emajasekova.thegreatestcocktailapp.dataClasses.CocktailPreview
import fr.emajasekova.thegreatestcocktailapp.network.ApiClient
import retrofit2.Call
import retrofit2.Response

@Composable
fun CocktailsScreen(modifier: Modifier, category: String) {

    val cocktails = remember { mutableStateOf<List<CocktailPreview>?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val call = ApiClient.retrofit.getCocktailsPreview(category)
        call.enqueue(object : retrofit2.Callback<CocktailFilterResponse> {
            override fun onResponse(
                call: Call<CocktailFilterResponse?>?,
                response: Response<CocktailFilterResponse?>?
            ) {
                cocktails.value = response?.body()?.cocktailPreviews
            }

            override fun onFailure(
                call: Call<CocktailFilterResponse?>?,
                t: Throwable?
            ) {
                TODO("Not yet implemented")
            }
        })
    }

    cocktails.value?.let { cocktails ->
        LazyColumn(
            modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cocktails) { cocktail ->
                Card(Modifier.clickable {
                    val intent = Intent(context, DetailCocktailActivity::class.java)
                    intent.putExtra(DetailCocktailActivity.DRINKID, cocktail.idDrink)
                    context.startActivity(intent)
                }) {
                    Row() {
                        AsyncImage(
                            model = cocktail.strDrinkThumb,
                            "",
                            Modifier.width(80.dp)
                                .height(80.dp)
                                .clip(CircleShape)
                        )
                    }

                    Text(
                        "${cocktail.strDrink}",
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}