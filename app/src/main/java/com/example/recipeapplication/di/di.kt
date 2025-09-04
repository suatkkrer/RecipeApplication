package com.example.recipeapplication.di

import com.example.recipeapplication.data.remote.RecipeApi
import com.example.recipeapplication.data.repository.RecipeRepositoryImpl
import com.example.recipeapplication.domain.repository.RecipeRepository
import com.example.recipeapplication.domain.usecase.GetMealsByCategoryUseCase
import com.example.recipeapplication.domain.usecase.GetMealsByRegionUseCase
import com.example.recipeapplication.presentation.viewmodels.MealCategoryViewModel
import com.example.recipeapplication.presentation.viewmodels.MealRegionViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
//    single {
//        Room.databaseBuilder(
//            androidContext(),
//            RecipeDatabase::class.java,
//            "recipe_db"
//        ).build()
//    }
//
//    single { get<RecipeDatabase>().recipeDao() }
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
    single { GetMealsByCategoryUseCase(get<RecipeRepository>()) }
    single { GetMealsByRegionUseCase(get<RecipeRepository>()) }
    viewModel { MealCategoryViewModel(get()) }
    viewModel { MealRegionViewModel(get()) }
}

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    single { moshi }
    single {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }
    single<RecipeApi> { get<Retrofit>().create(RecipeApi::class.java) }
}