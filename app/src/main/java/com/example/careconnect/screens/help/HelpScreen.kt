package com.example.careconnect.screens.help

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun HelpScreen(
    navController: NavController
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What do you Need Help With?",
            color = my_secondary,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.95f), thickness = 3.dp, color = my_secondary)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "How Does the Diagnostic Work?",
            color = my_primary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "You will first be asked some general health information regarding your age, height weight, and gender.\n\nNext, you will be prompted to enter your diet level, activity level,  drug/ alcohol usage level, and stress level. \n\nFinally, you will be asked for your any pre- existing conditions that you know of as well as any symptoms that you may be currently experiencing\n\n After inputting these values, our built in generative AI will give you a comprehensive overview of your health and potential diseases you may be prone to as well as prevention guidelines. Note that this is an AI, and should not be taken as legitimate advice from a licensed medical professional.",
            color = my_secondary,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.95f), thickness = 3.dp, color = my_secondary)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "How Does the Chat Work?",
            color = my_primary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "First, navigate to the Profile Screen. Under the section titled \"Account Information\", you should see a tab called Connection ID. There each user can find there unique connection ID. \n\n Now navigate back to the Chat Screen. Locate the plus icon on the bottom of the screen. After clicking it, you should be prompted to enter a connection ID. Ask your doctor for their connection ID and enter it here. Now, at the top of the screen, you should see a card with your doctor's name and email. Click on it and you should be able to begin chatting with your doctor!",
            color = my_secondary,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.95f), thickness = 3.dp, color = my_secondary)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "How Does the Vaccination Checker Work?",
            color = my_primary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Enter your child's birth date and click the submit button. The app will calculate and find the vaccinations needed for your child based on their age. Remember, consult a real medical professional before having any vaccinations administered to your child.",
            color = my_secondary,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}