package com.example.careconnect.screens.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.careconnect.R
import com.example.careconnect.navigation.Screen
import com.example.careconnect.ui.theme.dark_white
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    navController: NavController
) {
    LazyColumn {
        item { HeaderText("Settings") }

        item { ProfileCardUI(navController) }

        item { GeneralOptionsUI() }

        item { SupportOptionsUI() }

    }
}

@Composable
fun HeaderText(
    text : String
) {
    Text(
        text = text,
        color = my_secondary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 10.dp),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    )
}

@Composable
fun ProfileCardUI(
    navController : NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .padding(20.dp)
            .shadow(elevation = 8.dp),
        backgroundColor = dark_white,
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 28.dp,
                    end = 28.dp,
                    top = 20.dp,
                    bottom = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Check Your Profile",
                    color = my_secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Button(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(0)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = my_primary
                    ),
                    contentPadding = PaddingValues(horizontal = 30.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 2.dp,
                        pressedElevation = 4.dp
                    ),
                ) {
                    Text(
                        text = "View",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.blank_profile),
                contentDescription = "",
                modifier = Modifier.height(120.dp)
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun GeneralOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "General",
            color = my_secondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        GeneralSettingItem(

            icon = R.drawable.notification_bell,
            mainText = "Notifications",
            subText = "Customize which notifications you receive",
            onClick = {

            }
        )
        GeneralSettingItem(
            icon = R.drawable.security_shield,
            mainText = "Privacy Policy",
            subText = "View the privacy policy",
            onClick = {}
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun GeneralSettingItem(
    icon: Int,
    mainText: String,
    subText: String,
    onClick: () -> Unit
) {
    Card(
        onClick = { onClick() },
        backgroundColor = dark_white,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .shadow(elevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = my_primary,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(50.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Text(
                        text = mainText,
                        color = my_secondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = subText,
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.offset(y = (-4).dp)
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )

        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SupportOptionsUI() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Support",
            color = my_secondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        SupportItem(
            icon = R.drawable.contact_question,
            mainText = "Contact",
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:") // only email apps should handle this
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("support@example.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Support Request")
                }
                context.startActivity(Intent.createChooser(intent, "Send Email"))
            }
        )
        SupportItem(
            icon = R.drawable.feedback,
            mainText = "Feedback",
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:") // only email apps should handle this
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("support@example.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "CareConnect Feedback")
                }
                context.startActivity(Intent.createChooser(intent, "Send Feedback"))
            }
        )
        SupportItem(
            icon = R.drawable.magnifying_page,
            mainText = "Terms and Conditions",
            onClick = {

            }
        )
        SupportItem(
            icon = R.drawable.logo,
            mainText = "About",
            onClick = {

            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun SupportItem(
    icon: Int,
    mainText: String,
    onClick: () -> Unit
) {
    Card(
        onClick = { onClick() },
        backgroundColor = dark_white,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .shadow(elevation = 8.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = my_primary,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(50.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = mainText,
                    color = my_secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showSystemUi = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(navController = rememberNavController())
}