package tech.gauri.justforkicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.gauri.justforkicks.model.Category
import tech.gauri.justforkicks.ui.theme.JustForKicksTheme

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
        // View pager for shoes cards
        ShoeList()
    }
}

@Composable
fun ShoeList() {
    TODO("Not yet implemented")
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