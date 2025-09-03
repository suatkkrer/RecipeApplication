package com.example.recipeapplication.presentation.screens.homePage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.recipeapplication.presentation.viewmodels.HomePageViewModel
import com.example.recipeapplication.presentation.viewmodels.SearchUiState
import com.example.recipeapplication.ui.theme.RecipeApplicationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            RecipeApplicationTheme {
                MyScreen()
            }
        }
    }
}


@Composable
fun MyScreen(
    viewModel: HomePageViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState) {
        is SearchUiState.Error -> {}
        SearchUiState.Idle -> {}
        SearchUiState.Loading -> {}
        is SearchUiState.Success -> {
            InfoCard(state = uiState as SearchUiState.Success,
                modifier = Modifier.padding(top = 100.dp))
        }
    }
}

@Composable
fun InfoCard(
    state: SearchUiState.Success,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {

            AsyncImage(
                model = state.items.meals.first().strMealThumb,
                contentDescription = null,
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = state.items.meals.first().strMeal ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (!state.items.meals.first().idMeal.isNullOrBlank()) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = state.items.meals.first().idMeal ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (!state.items.meals.first().idMeal.isNullOrBlank()) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = state.items.meals.first().idMeal ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}