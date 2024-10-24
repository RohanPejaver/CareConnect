package com.example.careconnect.screens.diagnostic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.components.Logo
import com.example.careconnect.core.Constants.EMPTY_STRING
import com.example.careconnect.navigation.Screen
import com.example.careconnect.screens.diagnostic.components.InputField
import com.example.careconnect.ui.theme.my_primary
import kotlinx.coroutines.delay

@Composable
fun DiagnosticThree(viewmodel: DiagnosticViewModel, navController: NavController) {
    var preexistingCond by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    var symptoms by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )

    var triggerEffect by remember { mutableStateOf(false) }

    val isButtonEnabled = preexistingCond.text.isNotBlank() && symptoms.text.isNotBlank()

    LaunchedEffect(triggerEffect) {
        if (triggerEffect) {
            viewmodel.fetchAllUserData()
            viewmodel.interpretData()
            navController.navigate(Screen.Result.route)
            triggerEffect = false // Reset the trigger after navigation
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, top = 20.dp)
        ){
            Image(painter = painterResource(id = R.drawable.back_arrow), contentDescription = "Back Button", colorFilter = ColorFilter.tint(
                my_primary),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate(Screen.Library.route) }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Condition Health Information",
            fontWeight = FontWeight.Bold,
            color = my_primary,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        InputField(
            input = preexistingCond,
            onInputValueChange = { newValue ->
                preexistingCond = newValue
            },
            text = "Enter any pre-existing conditions you may have (eg. diabetes, cancer)"
        )
        Spacer(modifier = Modifier.height(12.dp))
        InputField(
            input = symptoms,
            onInputValueChange = { newValue ->
                symptoms = newValue
            },
            text = "Enter any symptoms you experience (eg. coughing, wheezing)"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewmodel.addConditionHealthData(preexistingCond.text + "are the user's pre-existing conditions", symptoms.text + "are some symptoms the user is experiencing")
                triggerEffect = true
            },
            modifier = Modifier
                .fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(containerColor = my_primary),
        ) {
            Text(
                text = "Submit",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.9f))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            HorizontalDivider(modifier = Modifier.fillMaxWidth(0.33f), color = Color.LightGray, thickness = 3.dp)
            Spacer(modifier = Modifier.width(4.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth(0.5f), color = Color.LightGray, thickness = 3.dp)
            Spacer(modifier = Modifier.width(4.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth(1f), color = my_primary, thickness = 4.dp)
        }
    }
}