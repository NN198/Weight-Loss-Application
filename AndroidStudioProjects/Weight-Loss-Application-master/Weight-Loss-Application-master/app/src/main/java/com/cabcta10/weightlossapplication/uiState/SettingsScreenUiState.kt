package com.cabcta10.weightlossapplication.uiState

import com.cabcta10.weightlossapplication.entity.Settings
import kotlinx.coroutines.flow.Flow

data class SettingsScreenUiState(
    val groceryStoreCoordinates: GroceryCoordinates = GroceryCoordinates(),
//    val userValues: userValues= userValues("", userInput.height),
    val userInput: userInput= userInput(),
    val userValues: userValues= userValues("", ""),
)

data class GroceryCoordinates(
    val latitude : String = "",
    val longitude : String = "",
)


data class userInput(
    val height : String = "",
    val weight : String = "",
    val targetWeight : String = "",
    val defaultStepCount : String = "",
    val waterIntake : String = "",
    val sleepTime : String = "",
    val wakeUpTime : String = "",
)

data class userValues(val key: String, val value: String = "")
fun SettingsScreenUiState.toSettings() : Settings = Settings(
    id = 1,
    latitude  = groceryStoreCoordinates.latitude.toDouble(),
    longitude =  groceryStoreCoordinates.longitude.toDouble(),
     height = userInput.height.toDouble(),
    weight = userInput.weight.toDouble(),
    targetWeight = userInput.targetWeight.toDouble(),
    defaultStepCount = userInput.defaultStepCount.toDouble(),
    waterIntake = userInput.waterIntake.toDouble(),
)