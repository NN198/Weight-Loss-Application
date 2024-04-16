package com.cabcta10.weightlossapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cabcta10.weightlossapplication.AppProvider
import com.cabcta10.weightlossapplication.R
import com.cabcta10.weightlossapplication.uiState.GroceryCoordinates
import com.cabcta10.weightlossapplication.uiState.userInput
import com.cabcta10.weightlossapplication.uiState.userValues
import com.cabcta10.weightlossapplication.viewModel.SettingsViewModel
import kotlinx.coroutines.launch


@Composable
fun GroceryStoreCoordinates(groceryCoordinates: GroceryCoordinates,
                            updateStoreCoordinates: (GroceryCoordinates)-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 220.dp)
    ) {
        Text(text = "Enter Grocery Coordinates", modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = groceryCoordinates.latitude,
            onValueChange = { updateStoreCoordinates(groceryCoordinates.copy(latitude = it)) },
            label = { Text("Latitude") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = groceryCoordinates.longitude,
            onValueChange = { updateStoreCoordinates(groceryCoordinates.copy(longitude = it)) },
            label = { Text("Longitude") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ApplySettings(
    onCancelClick: () -> Unit,
    onApplyClick: () -> Unit
) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            // Content of your settings here

            Spacer(modifier = Modifier.weight(1f))

            // Buttons at the bottom
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onCancelClick) {
                    Text(text = "Cancel")
                }
                Button(onClick = onApplyClick) {
                    Text(text = "Apply")
                }
            }
        }

}

@Composable
fun ApplicationTitle(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.dumbell),
                contentDescription = null,
                //contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
            Text(
                text = "Weight Loss",
                modifier = Modifier.padding(10.dp),
                fontSize = 25.sp
            )
        }
    }
}

@Composable
fun UserDetailsScreen(item: userValues, i: userInput, updateUserSettings: (userInput)-> Unit)

{

    Text(text = item.key, modifier = Modifier.padding(10.dp))
    OutlinedTextField(
        value = item.value,
        onValueChange = { updateUserSettings(i.copy(it)) },
        label = { Text(item.value) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
    )

    Text(text = "Weight", modifier = Modifier.padding(10.dp))
    OutlinedTextField(
        value = userValues.weight,
        onValueChange = { updateUserSettings(userValues.copy(weight = it)) },
        label = { Text("Weight") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
    )
    //Text(text = "Set Target Weight", modifier = Modifier.padding(10.dp))
    OutlinedTextField(
        value = userValues.targetWeight,
        onValueChange = { updateUserSettings(userValues.copy(targetWeight = it)) },
        label = { Text("Target Weight") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
    )
    //Text(text = "Step Count", modifier = Modifier.padding(10.dp))
    OutlinedTextField(
        value = userValues.defaultStepCount,
        onValueChange = { updateUserSettings(userValues.copy(defaultStepCount = it)) },
        label = { Text("Step Count") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
    )
    //Text(text = "Water Intake", modifier = Modifier.padding(10.dp))
    OutlinedTextField(
        value = userValues.waterIntake,
        onValueChange = { updateUserSettings(userValues.copy(targetWeight = it)) },
        enabled = false,
        label = { Text("Water Intake") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
    )
    //Text(text = "Sleep time", modifier = Modifier.padding(10.dp))
    OutlinedTextField(
        value = userValues.sleepTime,
        onValueChange = { updateUserSettings(userValues.copy(sleepTime = it)) },
        label = { Text("Sleep time") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
    )
    //Text(text = "Wake up time", modifier = Modifier.padding(10.dp))
    OutlinedTextField(
        value = userValues.wakeUpTime,
        onValueChange = { updateUserSettings(userValues.copy(wakeUpTime = it)) },
        label = { Text("Wake up time") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
    )


}



@Composable
fun userDetailsUpdate (userValues : userValues,
                       userInput: userInput,

                       updateUserSettings: (userInput)-> Unit
                       ) {

    var itemsList = listOf(
        userValues("Height",userInput.height),userValues("Weight",userInput.weight),userValues("Target Weight",userInput.targetWeight), userValues("Target Weight",""),userValues("Target Weight",userInput.defaultStepCount), userValues("Target Weight",""),userValues("Target Weight",""),userValues("Target Weight",""),userValues("Target Weight","") )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 120.dp)
    ) {
        LazyColumn()
        {
            items(itemsList)
            {
                    item->
                UserDetailsScreen(item,userInput,updateUserSettings)

            }
        }

    }
}



    /*
    defaultStepCount
    waterIntake
    sleepTime
    wakeUpTime
     */



@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = viewModel(factory = AppProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val settingsScreenUiState by settingsViewModel.settingsScreenUiState.collectAsState()
    ApplicationTitle()
    userDetailsUpdate(userValues = settingsScreenUiState.userValues,userInput= settingsScreenUiState.userInput,
        updateUserSettings = settingsViewModel::updateUserSettings)
    //GroceryStoreCoordinates(groceryCoordinates = settingsScreenUiState.groceryStoreCoordinates,
    //updateStoreCoordinates = settingsViewModel::updateStoreCoordinates
    //)

    ApplySettings({  }, {
        coroutineScope.launch {
            settingsViewModel.saveSettings()
        }
    })
}

