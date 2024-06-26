package com.cabcta10.weightlossapplication.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cabcta10.weightlossapplication.AppProvider
import com.cabcta10.weightlossapplication.R
import com.cabcta10.weightlossapplication.uiState.GroceryCoordinates
import com.cabcta10.weightlossapplication.uiState.UserUpdateValues
import com.cabcta10.weightlossapplication.viewModel.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun GroceryStoreCoordinates(groceryCoordinates: GroceryCoordinates,
                            updateStoreCoordinates: (GroceryCoordinates)-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
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
    val (saveClicked, setSaveClicked) = remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 90.dp)
    ) {
        // Content of your settings here

        Spacer(modifier = Modifier.weight(1f))

        if (saveClicked) {
            // Show the Snackbar and reset saveClicked after a short delay
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = { setSaveClicked(false) }) {
                        Text(text = stringResource(id = android.R.string.ok))
                    }
                },
                content = { Text("Changes saved successfully!") },
            )
        }
        // Buttons at the bottom
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onCancelClick) {
                Text(text = "Reset")

            }
            Button(onClick = { onApplyClick;
                setSaveClicked(true) }) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun userDetailsUpdate (userUpdateValues: UserUpdateValues,
                       updateUserDetailsValue: (UserUpdateValues)-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 120.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(text = "Enter The Details Below", modifier = Modifier.padding(20.dp))
        OutlinedTextField(
            value = userUpdateValues.defaultStepCount,
            onValueChange = { updateUserDetailsValue(userUpdateValues.copy(defaultStepCount = it)) },
            label = { Text("Step Count Per Day") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))

        OutlinedTextField(
            value = userUpdateValues.waterIntake,
            onValueChange = { updateUserDetailsValue(userUpdateValues.copy(waterIntake = it)) },
            //enabled = false,
            label = { Text("Water Intake") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Select the Grocery and Gym Locations: ", modifier = Modifier.padding(10.dp))
        var isExpanded by remember {
            mutableStateOf(value = false)
        }
        var locations by remember {
            mutableStateOf(value = "")
        }
        var latitude by remember {
            mutableStateOf(value = "")
        }
        var longitude by remember {
            mutableStateOf(value = "")
        }
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {
            isExpanded = it
        },
        ) {

            TextField(
                value = locations,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .fillMaxWidth(1.2f) // Adjust the width here
                    .padding(end = 20.dp) // Add padding to match your previous code
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded,


                onDismissRequest = { isExpanded = false},
            ) {
                DropdownMenuItem(text = {
                    Text(text = "Aldi"
                    )},
                    onClick = {
                        locations = "Aldi"
                        latitude = "19"
                        longitude = "21"
                        isExpanded = false
                        updateUserDetailsValue(userUpdateValues.copy(groceryLocationLatitude = latitude, groceryLocationLongitude = longitude))
                    },
                )
                DropdownMenuItem(text = {
                    Text(text = "Tesco") },
                    onClick = {
                        locations = "Tesco"
                        latitude = "219"
                        longitude = "221"
                        isExpanded = false
                        updateUserDetailsValue(userUpdateValues.copy(groceryLocationLatitude = latitude, groceryLocationLongitude = longitude))
                    })
            }

        }

        /*OutlinedTextField(
           value = userUpdateValues.groceryLocation,
           onValueChange = { updateUserDetailsValue(userUpdateValues.copy(groceryLocation = it)) },
           label = { Text("Grocery Location") },
           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
           modifier = Modifier.fillMaxWidth()
       )*/


        Spacer(modifier = Modifier.padding(16.dp))
        var isExpandedGym by remember {
            mutableStateOf(value = false)
        }
        var locationsGym by remember {
            mutableStateOf(value = "")
        }
        var latitudeGym by remember {
            mutableStateOf(value = "")
        }
        var longitudeGym by remember {
            mutableStateOf(value = "")
        }
        ExposedDropdownMenuBox(expanded = isExpandedGym, onExpandedChange = {
            isExpandedGym = it
        }) {

            TextField(
                value = locationsGym,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedGym)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .fillMaxWidth(1.2f) // Adjust the width here
                    .padding(end = 20.dp) // Add padding to match your previous code
                    .menuAnchor())
            ExposedDropdownMenu(
                expanded = isExpandedGym,
                onDismissRequest = { isExpandedGym = false}
            ) {
                DropdownMenuItem(text = {
                    Text(text = "Strath Sport") },
                    onClick = {
                        locationsGym = "Strath Sport"
                        latitudeGym = "119"
                        longitudeGym = "121"
                        isExpandedGym = false
                        updateUserDetailsValue(userUpdateValues.copy(gymLocationLatitude = latitudeGym, gymLocationLongitude = longitudeGym))
                    },
                )
                DropdownMenuItem(text = {
                    Text(text = "Glasgow Gym") },
                    onClick = {
                        locationsGym = "Glasgow Gym"
                        latitudeGym = "319"
                        longitudeGym = "321"
                        isExpandedGym = false
                        updateUserDetailsValue(userUpdateValues.copy(gymLocationLatitude = latitudeGym, gymLocationLongitude = longitudeGym))
                    })
            }

        }

        /*OutlinedTextField(
            value = userUpdateValues.gymLocation,
            onValueChange = { updateUserDetailsValue(userUpdateValues.copy(gymLocation = it)) },
            label = { Text("Gym Location") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )*/
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            value = userUpdateValues.sleepHours,
            onValueChange = { updateUserDetailsValue(userUpdateValues.copy(sleepHours = it)) },
            label = { Text("Sleep Hours") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun userDetailsReset(userUpdateValues: UserUpdateValues,
                     updateUserDetailsValue: (UserUpdateValues)-> Unit
) {
    updateUserDetailsValue(
        userUpdateValues.copy(
            waterIntake = "",
            defaultStepCount = "",
            sleepHours = ""
        )
    )

}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingsScreen(
    context: Context = LocalContext.current,
    settingsViewModel: SettingsViewModel = viewModel(factory = AppProvider.Factory(context))
) {

    val coroutineScope = rememberCoroutineScope()
    val settingsScreenUiState by settingsViewModel.settingsScreenUiState.collectAsState()
    ApplicationTitle()
    userDetailsUpdate(
        userUpdateValues = settingsScreenUiState.userUpdateValues,
        updateUserDetailsValue = settingsViewModel::updateUserDetailsValue,

        )
    /*GroceryStoreCoordinates(groceryCoordinates = settingsScreenUiState.groceryStoreCoordinates,
        updateStoreCoordinates = settingsViewModel::updateStoreCoordinates
    )*/

    ApplySettings({  coroutineScope.launch {
        settingsViewModel.deleteSettings()
        userDetailsReset(userUpdateValues = settingsScreenUiState.userUpdateValues,
            updateUserDetailsValue = settingsViewModel::updateUserDetailsValue)
    } }, {
        coroutineScope.launch {
            settingsViewModel.saveSettings()
        }
    })

}






