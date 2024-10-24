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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

@Composable
fun DiagnosticTwo(viewmodel: DiagnosticViewModel, navController: NavController) {
    var diet by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    var activity by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    var substanceLevel by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    var stressLevel by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )

    val isButtonEnabled = diet.text.isNotBlank() && activity.text.isNotBlank() && substanceLevel.text.isNotBlank() && stressLevel.text.isNotBlank()

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
                color = my_primary),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate(Screen.Library.route) }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Lifestyle Health Information",
            fontWeight = FontWeight.Bold,
            color = my_primary,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        InputField(
            input = diet,
            onInputValueChange = { newValue ->
                diet = newValue
            },
            text = "Enter your perceived diet level (eg. good, bad, etc.)"
        )
        Spacer(modifier = Modifier.height(12.dp))
        InputField(
            input = activity,
            onInputValueChange = { newValue ->
                activity = newValue
            },
            text = "Enter your activity level (eg. good, bad, etc.)"
        )
        Spacer(modifier = Modifier.height(12.dp))
        InputField(
            input = substanceLevel,
            onInputValueChange = { newValue ->
                substanceLevel = newValue
            },
            text = "Enter your drug & alcohol usage level (eg. good, bad, etc.)"
        )
        Spacer(modifier = Modifier.height(12.dp))
        InputField(
            input = stressLevel,
            onInputValueChange = { newValue ->
                stressLevel = newValue
            },
            text = "Enter how stressed you are (eg. 7/10)"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewmodel.addLifestyleHealthData(diet.text + "diet level/ rating", activity.text + "activity level/ rating", substanceLevel.text + "alcohol & drug usage level/ rating", stressLevel.text + "stress level/ rating")
                navController.navigate(Screen.DiagnosticThree.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(containerColor = my_primary),
            enabled = isButtonEnabled
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
            HorizontalDivider(modifier = Modifier.fillMaxWidth(0.5f), color = my_primary, thickness = 4.dp)
            Spacer(modifier = Modifier.width(4.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth(1f), color = Color.LightGray, thickness = 3.dp)
        }
    }
}