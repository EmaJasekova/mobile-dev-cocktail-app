package fr.emajasekova.thegreatestcocktailapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import fr.emajasekova.thegreatestcocktailapp.dataClasses.Cocktail
import fr.emajasekova.thegreatestcocktailapp.dataClasses.CocktailResponse
import fr.emajasekova.thegreatestcocktailapp.models.AppBarState
import fr.emajasekova.thegreatestcocktailapp.network.ApiClient
import retrofit2.Call
import retrofit2.Response

@Composable
fun RandomCocktailScreen(modifier: Modifier, onComposing: (AppBarState) -> Unit) {
    var cocktail = remember { mutableStateOf<Cocktail?>(null) }

    LaunchedEffect(Unit) {
        onComposing (
            AppBarState("Random Cocktail",
                actions = { DetailCocktailTopButton(cocktail.value) }
            )
        )

        val call = ApiClient.retrofit.getRandomCocktail()
//        call.enqueue(object : retrofit2.Callback<CocktailResponse> {
//            override fun onResponse(
//                call: Call<CocktailResponse?>?,
//                response: Response<CocktailResponse?>?
//            ) {
//                cocktail.value = response?.body()?.cocktails?.first()
//            }
//            override fun onFailure(
//                call: Call<CocktailResponse?>?,
//                t: Throwable?
//            ) {
//                Log.e("request", "getrandom failed ${t?.message}")
//            }
//        })
    }

//    cocktail.value?
}

@Composable
fun DetailCocktailTopButton(cocktail: Cocktail?) {
    val context = LocalContext.current

    IconButton({
        Toast
            .makeText(context, "Add to favourites", Toast.LENGTH_LONG)
            .show()
    }) {
        Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Add to favourites"
        )
    }
}

