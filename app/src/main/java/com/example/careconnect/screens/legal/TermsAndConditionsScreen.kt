package com.example.careconnect.screens.legal

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.navigation.Screen
import com.example.careconnect.ui.theme.my_primary

@Composable
fun TermsAndConditionsScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(8.dp)
    ){
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, top = 20.dp)
        ){
            Image(painter = painterResource(id = R.drawable.back_arrow), contentDescription = "Back Button", colorFilter = ColorFilter.tint(
                my_primary
            ),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate(Screen.Settings.route) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text =
        "Effective Date: 10/24/2024\n" +
                "\n" +
                "1. Acceptance of Terms\n" +
                "\n" +
                "By downloading or using CareConnect (the \"App\"), you agree to these Terms and Conditions. If you do not agree, you may not use the App.\n" +
                "\n" +
                "2. Use of the App\n" +
                "\n" +
                "The App is designed to provide healthcare information and connect users with healthcare professionals. You agree to use the App in compliance with all applicable laws and for lawful purposes only.\n" +
                "\n" +
                "3. Health Information Disclaimer\n" +
                "\n" +
                "The App allows you to input and share health information with doctors. However, the App does not provide medical advice, diagnoses, or treatment. Any medical advice you receive from healthcare providers through the App is solely their responsibility.\n" +
                "\n" +
                "4. Location Services\n" +
                "\n" +
                "The App may access your location to provide certain features, such as finding nearby healthcare providers. You agree to allow the App to collect and use your location data as described in the Privacy Policy.\n" +
                "\n" +
                "5. User Responsibilities\n" +
                "\n" +
                "Accuracy of Information: You are responsible for providing accurate and complete health and personal information.\n" +
                "Account Security: You must protect your login credentials and are responsible for any activity that occurs under your account.\n" +
                "Prohibited Activities: You agree not to misuse the App, including but not limited to, engaging in fraudulent activities, distributing malware, or violating the privacy rights of others.\n" +
                "6. Limitation of Liability\n" +
                "\n" +
                "To the fullest extent permitted by law, CareConnect and its affiliates will not be liable for any damages arising from your use of the App, including but not limited to, any indirect, incidental, or consequential damages, even if we have been advised of the possibility of such damages.\n" +
                "\n" +
                "7. Termination\n" +
                "\n" +
                "We reserve the right to terminate or suspend your access to the App at any time, with or without cause, for violating these Terms or for any other reason.\n" +
                "\n" +
                "8. Modifications to the Terms\n" +
                "\n" +
                "We may update these Terms and Conditions at any time. Any changes will be effective upon posting within the App, and your continued use of the App signifies your acceptance of the updated Terms.\n" +
                "\n" +
                "9. Governing Law\n" +
                "\n" +
                "These Terms and Conditions are governed by the laws of 78681, Round Rock, TX, U.S, and any disputes will be resolved in accordance with the courts of TX.\n" +
                "\n" +
                "10. Contact Us\n" +
                "\n" +
                "If you have any questions about these Terms and Conditions, please contact us at +1 (512)- 758- 5282 or coolmixer10@gmail.com.\n" +
                "\n")
    }

}