package com.example.careconnect.screens.vaccination

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.careconnect.R
import com.example.careconnect.model.VaccinationData
import com.example.careconnect.navigation.Screen
import com.example.careconnect.screens.library.VaccineCard
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@Composable
fun VaccinationScreen(viewModel: VaccinationViewModel, navController: NavController) {
    var dateOfBirth by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf("") }
    var vaccinations by remember { mutableStateOf(emptyList<String>()) }
    var errorMessage by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()){
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // Align vertically centered
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Back Button",
                    colorFilter = ColorFilter.tint(my_primary),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate(Screen.Library.route) }
                )
                Spacer(modifier = Modifier.weight(0.75f))
                Text(
                    text = "Vaccine Checker (Child)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = my_primary,
                    modifier = Modifier.align(Alignment.CenterVertically) // Ensures vertical alignment
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Enter Date of Birth (YYYY-MM-DD)", color = my_secondary, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("Date of Birth", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                cursorColor = my_primary,
                focusedIndicatorColor = Color.White.copy(0.95f),
                unfocusedIndicatorColor = Color.White.copy(0.95f)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        androidx.compose.material3.Button(
            onClick = {
                try {
                    val parsedDate = LocalDate.parse(dateOfBirth.text, DateTimeFormatter.ISO_DATE)
                    val today = LocalDate.now()
                    val calculatedAge = Period.between(parsedDate, today)
                    age = "${calculatedAge.years} years, ${calculatedAge.months} months"

                    // Call the function to get vaccinations based on age
                    val ageInMonths = calculatedAge.years * 12 + calculatedAge.months
                    vaccinations = viewModel.getVaccinationsForAge(ageInMonths)
                    errorMessage = ""
                } catch (e: Exception) {
                    errorMessage = "Invalid date format. Please use YYYY-MM-DD."
                }
                val vaccinationData = VaccinationData(dateOfBirth.text, vaccinations)
                viewModel.addVaccinationData(vaccinationData)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = my_secondary),
            ) {
            Text("Get Vaccinations", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colors.error)
        } else {
            Text(text = "Age: $age", color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Vaccinations needed:", color = my_secondary, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)

            vaccinations.forEach { vaccine ->
                Text(text = vaccine, style = MaterialTheme.typography.body1)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        VaccineCard(navController = rememberNavController()) {

        }
    }
}
