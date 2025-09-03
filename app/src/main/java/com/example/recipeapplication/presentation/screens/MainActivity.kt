package com.example.recipeapplication.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.recipeapplication.presentation.navigation.AppScaffold
import com.example.recipeapplication.ui.theme.RecipeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            RecipeApplicationTheme {
                AppScaffold()
            }
        }
    }
}
