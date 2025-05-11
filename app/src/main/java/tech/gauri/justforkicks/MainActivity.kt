package tech.gauri.justforkicks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import tech.gauri.justforkicks.model.Category
import tech.gauri.justforkicks.model.Shoe
import tech.gauri.justforkicks.ui.theme.JustForKicksTheme

val sampleShoes = listOf(
    Shoe(
        id = 1,
        title = "Air Max 90",
        category = Category.MEN,
        brand = "Nike",
        price = 129.99f,
        imageUrl = "https://images.unsplash.com/photo-1560072810-1cff5ecfc83e?auto=format&fit=crop&w=640&q=80"
    ),
    Shoe(
        id = 2,
        title = "Ultraboost Light",
        category = Category.MEN,
        brand = "Adidas",
        price = 190.00f,
        imageUrl = "https://images.unsplash.com/photo-1575537302964-1f890d77b28c?auto=format&fit=crop&w=640&q=80"
    ),
    Shoe(
        id = 3,
        title = "Classic Leather",
        category = Category.WOMEN,
        brand = "Reebok",
        price = 74.95f,
        imageUrl = "https://images.unsplash.com/photo-1539186607619-609644c137a3?auto=format&fit=crop&w=640&q=80"
    ),
    Shoe(
        id = 4,
        title = "Gel-Kayano 30",
        category = Category.WOMEN,
        brand = "ASICS",
        price = 159.95f,
        imageUrl = "https://images.unsplash.com/photo-1612837017391-65cdce89f397?auto=format&fit=crop&w=640&q=80"
    ),
    Shoe(
        id = 5,
        title = "FuelCell Rebel v3",
        category = Category.SPORTS,
        brand = "New Balance",
        price = 129.99f,
        imageUrl = "https://images.unsplash.com/photo-1528702748617-7098d08d8ae1?auto=format&fit=crop&w=640&q=80"
    ),
    Shoe(
        id = 6,
        title = "Mercurial Superfly 9",
        category = Category.SPORTS,
        brand = "Nike",
        price = 289.95f,
        imageUrl = "https://images.unsplash.com/photo-1613214785113-06ea3c8ff9ea?auto=format&fit=crop&w=640&q=80"
    ),
    Shoe(
        id = 7,
        title = "Court Borough Low (Boys)",
        category = Category.BOYS,
        brand = "Nike",
        price = 54.99f,
        imageUrl = "https://images.unsplash.com/photo-1618354691409-2f0a7c4eb4a2?auto=format&fit=crop&w=640&q=80"
    ),
    Shoe(
        id = 8,
        title = "Grand Court 2.0 (Girls)",
        category = Category.GIRLS,
        brand = "Adidas",
        price = 49.99f,
        imageUrl = "https://images.unsplash.com/photo-1552346154-21dcb9d13214?auto=format&fit=crop&w=640&q=80"
    ),
    Shoe(
        id = 9,
        title = "Chuck Taylor All Star",
        category = Category.CASUAL,
        brand = "Converse",
        price = 65.00f,
        imageUrl = "https://images.unsplash.com/photo-1542293787938-c9e299b88049?auto=format&fit=crop&w=640&q=80"
    ),
    Shoe(
        id = 10,
        title = "Old Skool",
        category = Category.CASUAL,
        brand = "Vans",
        price = 69.95f,
        imageUrl = "https://images.unsplash.com/photo-1491553895911-0055eca6402d?auto=format&fit=crop&w=640&q=80"
    )
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustForKicksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(8.dp)
    ) {
        TitleText(stringResource(R.string.shoes_title))
        ShoesChips(onSelected = { onShoeChipClick() })
        ShoeCarousel(sampleShoes)
//        ShoeList()
    }
}


@Composable
fun ShoeList() {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoeCarousel(shoes: List<Shoe>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount  = { shoes.size }
    )
    HorizontalPager(
        pageSize = PageSize.Fixed(250.dp),
        modifier = modifier,
        state = pagerState
    ) { page ->
        ShoeCard(shoes[page])
    }
}

@Composable
fun ShoeCard(shoe: Shoe) {
    Card(shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(16.dp)
            .width(250.dp)
            .aspectRatio(3f / 4f),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .graphicsLayer { clip = false }
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.Cyan)
            )
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(shoe.imageUrl)
                .build(),
                contentDescription = "Shoe Image",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.shoe_sneaker),
                error = painterResource(R.drawable.shoe_sneaker),
                modifier = Modifier
                    .height(140.dp)
                    .offset(x = (48).dp)
            )
            Column {
                Text(shoe.title)
                Text(shoe.price.toString())
            }
        }
    }

}

fun onShoeChipClick() {
    
}

@Composable
fun ShoesChips(categories: List<Category> = Category.entries,
               selected: Category? = null,
               onSelected: () -> Unit
               ) {
    LazyRow {
        items(categories, key = { it.name }) {
            FilterChip(
                selected == it,
                onSelected,
                modifier = Modifier
                    .padding(4.dp),
                label = { Text(it.label) },
                enabled = true
            )
        }
    }
}

@Composable
fun TitleText(title: String, modifier: Modifier = Modifier) {
    Text(title,
        modifier = modifier
            .padding(horizontal = 8.dp),
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustForKicksTheme {
        HomeScreen()
    }
}

