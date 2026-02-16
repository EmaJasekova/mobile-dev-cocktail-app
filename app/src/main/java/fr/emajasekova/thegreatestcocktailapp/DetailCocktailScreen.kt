package fr.emajasekova.thegreatestcocktailapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailCocktailScreen(
    modifier: Modifier,
    drinkName: String,
    categories: List<Category>,
    glassType: GlassType,
    ingredients: List<Ingredient>,
    preparation: String
) {
    val padding = 20.dp
    Box(
        Modifier
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.black),
                        colorResource(R.color.purple_700)
                    )
                )
            )
            .fillMaxSize()
    ) {
        TopIcons() // TODO move to app top bar

        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.size(padding * 2))
            DrinkView(drinkName)

            Spacer(Modifier.size(padding))
            Categories(categories)

            Spacer(Modifier.size(padding))
            GlassTypeView(glassType)
            Spacer(Modifier.size(padding))

            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .width(350.dp)
            ) {
                Spacer(Modifier.size(padding))
                Ingredients(ingredients)

                Spacer(Modifier.size(padding))
                Preparation(preparation)
            }
        }
    }
}

@Composable
fun TopIcons() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        ButtonWithIcon(R.drawable.reload, "reload", {})
        ButtonWithIcon(R.drawable.heart, "heart", {})
    }
}

@Composable
fun ButtonWithIcon(iconId: Int, iconDescription: String, onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = iconDescription,
            modifier = Modifier
                .width(25.dp)
                .height(25.dp)
        )
    }
}

@Composable
fun DrinkView(drinkName: String) {
    Image(
        painter = painterResource(id = R.drawable.cosmopolitan),
        contentDescription = "Cosmopolitan",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .clip(CircleShape)
            .border(
                2.dp,
                colorResource(R.color.white),
                CircleShape
            )
    )
    Text(
        drinkName,
        fontSize = 40.sp,
        color = colorResource(R.color.white)
    )
}

@Composable
fun GlassTypeView(glassType: GlassType) {
    Row() {
        Icon(
            painter = painterResource(R.drawable.glass),
            contentDescription = "glass",
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
        )

        Text(glassType.name)
    }
}

@Composable
fun CategoryView(category: Category) {
    Box(
        Modifier
            .clip(CircleShape)
            .border(
                2.dp,
                colorResource(R.color.white),
                CircleShape
            )
            .background(
                Brush.horizontalGradient(
                    listOf(
                        colorResource(R.color.teal_200),
                        colorResource(R.color.teal_700)
                    )
                )
            )
    ) {

        Text(
            category.name,
            fontSize = 20.sp,
            color = colorResource(R.color.white)
        )
    }
}

@Composable
fun Categories(categories: List<Category>) {
    Row() {
        categories.forEach { category ->
            CategoryView(category)
        }
    }
}

@Composable
fun IngredientView(ingredient: Ingredient) {
    Row() {
        Text(ingredient.name)
        Text(ingredient.amount.toString())
        Text(ingredient.unit.name)
    }
}

@Composable
fun Ingredients(ingredients: List<Ingredient>) {
    val padding = 10.dp
    Column() {
        Card(
            Modifier
//            .border(
//                2.dp,
//                colorResource(R.color.white)
//            )
//            .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            Text(
                text = "Ingredients",
                modifier = Modifier.padding(15.dp),
            )
            ingredients.forEach { ingredient ->
                IngredientView(ingredient)
                Spacer(Modifier.size(padding))
            }
        }
    }
}

@Composable
fun Preparation(preparation: String) {
    val padding = 10.dp
    Column() {
        Card(
            Modifier
//            .border(
//                2.dp,
//                colorResource(R.color.white)
//            )
//            .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            Text(
                text = preparation,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}