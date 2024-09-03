package com.example.careconnect.screens.library

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.careconnect.R
import com.example.careconnect.ui.theme.my_secondary

@Composable
fun LibraryScreen(
    navController: NavController
) {
    var isImageOverlayVisible by remember { mutableStateOf(false) }
    var isDiseaseOverlayVisible by remember { mutableStateOf(false) }
    var imageResId by remember { mutableStateOf(R.drawable.vaccine_schedule_child) }
    var selectedDiseaseInfo by remember { mutableStateOf<DiseaseInfo?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                VaccineCard(
                    navController = navController,
                    onImageClick = { resId ->
                        imageResId = resId
                        isImageOverlayVisible = true
                    }
                )
            }

            item {
                DiseaseSearchScreen(onDiseaseClick = { diseaseInfo ->
                    selectedDiseaseInfo = diseaseInfo
                    isDiseaseOverlayVisible = true
                })
            }
        }

        if (isImageOverlayVisible) {
            ImageOverlay(
                navController = navController,
                imageResId = imageResId,
                onBackClick = { isImageOverlayVisible = false }
            )
        }

        if (isDiseaseOverlayVisible && selectedDiseaseInfo != null) {
            DiseaseOverlay(
                diseaseInfo = selectedDiseaseInfo!!,
                onBackClick = { isDiseaseOverlayVisible = false }
            )
        }
    }
}

@Composable
fun VaccineCard(
    navController: NavController,
    onImageClick: (Int) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Are you up to date on your vaccines?",
            fontSize = 20.sp,
            color = my_secondary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .shadow(elevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = my_secondary
                ),
            ) {
                Text("Card 1", modifier = Modifier.padding(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(bottom = 8.dp)
                        .clickable {
                            onImageClick(R.drawable.vaccine_schedule_child)
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.vaccine_schedule_child),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .shadow(elevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = my_secondary
                ),
            ) {
                Text("Card 2", modifier = Modifier.padding(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(bottom = 8.dp)
                        .clickable {
                            onImageClick(R.drawable.vaccine_schedule_adult)
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.vaccine_schedule_adult),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        TextButton(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=CDC+Vaccine+Schedule+for+2024&rlz=1C1ONGR_enUS1018US1018&oq=CDC+Vaccine+Schedule+for+2024&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIICAEQABgWGB4yCAgCEAAYFhgeMggIAxAAGBYYHjIICAQQABgWGB4yCAgFEAAYFhgeMggIBhAAGBYYHjIICAcQABgWGB4yDQgIEAAYhgMYgAQYigUyCggJEAAYgAQYogTSAQkxMTEyNmowajeoAgCwAgA&sourceid=chrome&ie=UTF-8"))
                context.startActivity(intent)
            }
        ) {
            Text(
                text = "For More Information",
                color = my_secondary,
                fontSize = 20.sp,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
fun ImageOverlay(
    navController: NavController,
    imageResId: Int,
    onBackClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.DarkGray.copy(0.7f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Vaccine Schedule",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Back Button",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            onBackClick()
                        }
                )
            }
        }
    }
}

@Composable
fun DiseaseCard(
    diseaseInfo: DiseaseInfo,
    onDiseaseClick: (DiseaseInfo) -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onDiseaseClick(diseaseInfo) },
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(5.dp)),
            painter = rememberAsyncImagePainter(diseaseInfo.image),
            contentDescription = diseaseInfo.title,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = my_secondary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .shadow(elevation = 8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = diseaseInfo.title,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = diseaseInfo.information,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DiseaseSearchScreen(onDiseaseClick: (DiseaseInfo) -> Unit) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val filteredDiseases = diseaseInfoItems.filter {
        it.title.contains(searchQuery.text, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        DiseaseSearchBar(searchQuery) {
            searchQuery = it
        }
        Spacer(modifier = Modifier.height(8.dp))
        DiseaseList(diseases = filteredDiseases, onDiseaseClick = onDiseaseClick)
    }
}

@Composable
fun DiseaseList(diseases: List<DiseaseInfo>, onDiseaseClick: (DiseaseInfo) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        diseases.forEach { disease ->
            DiseaseCard(diseaseInfo = disease, onDiseaseClick = onDiseaseClick)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun DiseaseSearchBar(searchQuery: TextFieldValue, onSearchQueryChange: (TextFieldValue) -> Unit) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        placeholder = { androidx.compose.material.Text(text = "Search for diseases") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun DiseaseOverlay(
    diseaseInfo: DiseaseInfo,
    onBackClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.DarkGray.copy(0.7f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(diseaseInfo.image),
                    contentDescription = diseaseInfo.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                Text(
                    text = diseaseInfo.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = diseaseInfo.information,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Back Button",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            onBackClick()
                        }
                )
            }
        }
    }
}

data class DiseaseInfo(
    val title : String,
    val information : String,
    val image : String,
    val link: String
)

val diseaseInfoItems = listOf(
    DiseaseInfo(
        title = "Influenza (Common Flu)",
        information = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        image = "https://i1.sndcdn.com/artworks-KSj6wtCCo1QkCISz-zzQJKA-t240x240.jpg",
        link = "https://www.google.com/search?q=osamason&sa=X&sca_esv=e125ad82533a0389&sca_upv=1&rlz=1C1ONGR_enUS1018US1018&sxsrf=ADLYWILGnoP_bsGseDtMWdj3bmBQHJ2L0g:1719770975355&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWd8nbOJfsBGGB5IQQO6L3J_86uWOeqwdnV0yaSF-x2jrJh7Dt5wV71ckxEPe_0GQym3cCb8Qr0OyHcl6-iLR-l3pOkQZHVA49hDfmi2FWVyengkU4q5mzj5_Z2BHQ-LFpfk2uvUHnN3h8kjN_4YSgAarDB-vg_srG72TMwwkiPNgBGmIHFDNjr3l3za4ZJpbiYaJu7g&ved=2ahUKEwiB1NKQ9oOHAxVPGtAFHWnFCi8QtKgLegQIAhAE&biw=1920&bih=991&dpr=1#vhid=Un_82SfGaPfglM&vssid=mosaic"
    ),
    DiseaseInfo(
        title = "boiaer (Common Flu)",
        information = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        image = "https://i1.sndcdn.com/artworks-KSj6wtCCo1QkCISz-zzQJKA-t240x240.jpg",
        link = "https://www.google.com/search?q=osamason&sa=X&sca_esv=e125ad82533a0389&sca_upv=1&rlz=1C1ONGR_enUS1018US1018&sxsrf=ADLYWILGnoP_bsGseDtMWdj3bmBQHJ2L0g:1719770975355&udm=2&fbs=AEQNm0Aa4sjWe7Rqy32pFwRj0UkWd8nbOJfsBGGB5IQQO6L3J_86uWOeqwdnV0yaSF-x2jrJh7Dt5wV71ckxEPe_0GQym3cCb8Qr0OyHcl6-iLR-l3pOkQZHVA49hDfmi2FWVyengkU4q5mzj5_Z2BHQ-LFpfk2uvUHnN3h8kjN_4YSgAarDB-vg_srG72TMwwkiPNgBGmIHFDNjr3l3za4ZJpbiYaJu7g&ved=2ahUKEwiB1NKQ9oOHAxVPGtAFHWnFCi8QtKgLegQIAhAE&biw=1920&bih=991&dpr=1#vhid=Un_82SfGaPfglM&vssid=mosaic"
    ),
)
