package com.example.careconnect.screens.profile.components


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.careconnect.R
import com.example.careconnect.screens.settings.HeaderText
import com.example.careconnect.ui.theme.dark_white
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@ExperimentalMaterialApi
@Composable
fun ProfileContent(
    signOut: () -> Unit,
    deleteAccount: () -> Unit,
    username: String,
    email: String,
    connectionId: String
) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )

    HeaderText(text = "Profile")
    Column (
        Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 0.dp)
    ){
        Spacer(modifier = Modifier.height(30.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(
                    model = selectedImageUri,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.blank_profile),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = username,
                    color = my_secondary,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(60.dp))
            Button(
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = my_primary
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 4.dp
                ),
            ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.Upload,
                        contentDescription = "Select profile picture",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Choose Image",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Account Information",
            color = my_secondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoItem(
            icon = Icons.Default.Email,
            mainText = "Registered Email",
            subText = email,
        )
        ProfileInfoItem(
            icon = Icons.Default.Add,
            mainText = "Connection ID (for Chats)",
            subText = connectionId,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Account Management",
            color = my_secondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        ProfileManagementItem(
            icon = Icons.AutoMirrored.Filled.Logout,
            mainText = "Sign Out",
            subText = "Sign out of CareConnect",
            confirmationText = "Are you sure you want to sign out?",
            onClick = {
                signOut()
            }
        )
        ProfileManagementItem(
            icon = Icons.Default.DeleteForever,
            mainText = "Delete Account",
            subText = "Delete your CareConnect account",
            confirmationText = "Are you sure you want to delete your account? This action cannot be undone!",
            onClick = {
                deleteAccount()
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun ProfileInfoItem(
    icon: ImageVector,
    mainText: String,
    subText: String,
) {
    Card(
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
                        imageVector = icon,
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
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = subText,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.offset(y = (-4).dp)
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ProfileManagementItem(
    icon: ImageVector,
    mainText: String,
    subText: String,
    confirmationText: String,
    onClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        onClick = { showDialog = true },
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
                        imageVector = icon,
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
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = subText,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.offset(y = (-4).dp)
                    )
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "Confirm Action",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            },
            text = {
                Text(
                    text = confirmationText,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(0.95f)
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onClick()
                }) {
                    Text(
                        "Confirm",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(
                        "Cancel",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            backgroundColor = my_secondary.copy(0.98f)
        )
    }
}
