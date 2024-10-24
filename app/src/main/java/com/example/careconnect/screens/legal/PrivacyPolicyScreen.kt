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
fun PrivacyPolicyScreen(navController: NavController) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
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
        Text(
            text = "Effective Date: 10/24/2024\n" +
                    "\n" +
                    "1. Introduction\n" +
                    "\n" +
                    "CareConnect (\"we,\" \"us,\" or \"our\") is committed to protecting your privacy. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you use our health application (the \"App\"). By using the App, you consent to the collection, storage, and use of your information as described in this policy.\n" +
                    "\n" +
                    "2. Information We Collect\n" +
                    "\n" +
                    "We may collect the following types of information:\n" +
                    "\n" +
                    "Personal Information: Name, email address, date of birth, gender, and contact information.\n" +
                    "Health Information: Health data such as medical history, vaccination status, diagnostic results, symptoms, and other relevant information you provide.\n" +
                    "Location Data: We may collect real-time location information to provide you with location-based services, such as finding nearby healthcare providers.\n" +
                    "Device Information: Information about your mobile device, such as your device ID, operating system, and usage data.\n" +
                    "3. How We Use Your Information\n" +
                    "\n" +
                    "We may use the information we collect to:\n" +
                    "\n" +
                    "Provide, maintain, and improve our App and services.\n" +
                    "Share your health information with healthcare professionals, including doctors, to enable them to provide medical advice or treatment.\n" +
                    "Respond to your inquiries and provide customer support.\n" +
                    "Send you relevant health information or updates.\n" +
                    "Comply with legal and regulatory requirements.\n" +
                    "4. Sharing Your Information\n" +
                    "\n" +
                    "We may share your information:\n" +
                    "\n" +
                    "With Doctors and Healthcare Providers: To facilitate medical advice and treatment.\n" +
                    "With Third-Party Service Providers: To help us operate the App and provide services to you, such as cloud storage or data analytics.\n" +
                    "For Legal Purposes: To comply with applicable laws, regulations, or legal processes.\n" +
                    "With Your Consent: When you explicitly authorize us to share your information.\n" +
                    "5. Data Security\n" +
                    "\n" +
                    "We use industry-standard security measures to protect your personal and health information. However, no security system is impenetrable, and we cannot guarantee the security of your data.\n" +
                    "\n" +
                    "6. Data Retention\n" +
                    "\n" +
                    "We retain your information as long as necessary to provide our services and fulfill the purposes outlined in this Privacy Policy unless a longer retention period is required by law.\n" +
                    "\n" +
                    "7. Your Rights\n" +
                    "\n" +
                    "Depending on your location, you may have certain rights regarding your data, such as:\n" +
                    "\n" +
                    "The right to access or correct your personal information.\n" +
                    "The right to request deletion of your data.\n" +
                    "The right to opt-out of certain uses of your information.\n" +
                    "8. Changes to This Policy\n" +
                    "\n" +
                    "We may update this Privacy Policy from time to time. Any changes will be posted in the App, and your continued use of the App will signify your acceptance of the updated policy.\n" +
                    "\n" +
                    "9. Contact Us\n" +
                    "\n" +
                    "If you have any questions about this Privacy Policy, please contact us at +1 (512)-758-5282 or coolmixer10@gmail.com."
        )
    }
}