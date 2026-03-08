package fr.emajasekova.thegreatestcocktailapp.network

import fr.emajasekova.thegreatestcocktailapp.dataClasses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    @GET("random.php")
//    fun getRandomCocktail(): Call<CocktailResponse>

    @GET("random.php")
    suspend fun getRandomCocktail(): CocktailResponse

    @GET("list.php?c=list")
    fun getCategories(): Call<CategoryListResponse>

    @GET("filter.php")
    fun getCocktailsPreview(@Query("c") categoryID: String): Call<CocktailFilterResponse>

    @GET("lookup.php")
    fun getDetailCocktail(@Query("i") drinkID: String): Call<CocktailResponse>
}
