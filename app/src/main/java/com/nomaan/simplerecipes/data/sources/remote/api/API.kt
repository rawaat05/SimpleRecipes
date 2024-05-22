package com.nomaan.simplerecipes.data.sources.remote.api

import com.nomaan.simplerecipes.data.sources.remote.dto.response.CategoriesResponse
import com.nomaan.simplerecipes.data.sources.remote.dto.response.CategoryMealsResponse
import com.nomaan.simplerecipes.data.sources.remote.dto.response.MealDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("1/categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("1/filter.php")
    suspend fun getCategoryMealsList(
        @Query("c") categoryName: String
    ): CategoryMealsResponse

    @GET("1/lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId: String
    ): MealDetailsResponse
}