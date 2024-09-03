package com.example.careconnect.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.careconnect.ui.theme.my_secondary

data class Doctor(
    val name: String,
    val type: String,
    val image: String,
    val link: String
)

val doctorItems = listOf(
    Doctor(
        name = "Mrudula Deshpande M.D.",
        type = "Pediatrician",
        image = "https://www.md.com/images/photos/thumbs/dr-mrudula-a-deshpande-md-55379749b9ff2.jpg",
        link = "https://doctordeshpande.com/"
    ),
    Doctor(
        name = "Robert Nason M.D.",
        type = "Otolaryngologist",
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTd26sTELPEOdYxjwajCfjS1CfSWLBrT3gH2g&s",
        link = "http://nasonmd.com/"
    ),
    Doctor(
        name = "Megyn L. Busse M.D.",
        type = "Ophthalmologist",
        image = "https://d1k13df5m14swc.cloudfront.net/photos/Dr-Franklin-Busse-MD-104089-zoom.jpg",
        link = "https://www.austinpediatricophthalmology.com/"
    ),
    Doctor(
        name = "Harashada Luhar M.D.",
        type = "Neonatology",
        image = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
        link = "https://www.google.com/search?sca_esv=20367276c75d363f&sca_upv=1&rlz=1C1ONGR_enUS1018US1018&sxsrf=ADLYWIItnWVKQi1M-ERqgTroK4RBwYLpSQ:1719001997274&q=Dr.+Harashada+Luhar+Beaumont&si=ACC90nxp5PteNmYcqmoXxlml2xQ2QM8noJmQUYMLhp4J5SbXMzZhEqDrsBM2SZlmvq0ibxq43xpVs3wG9HQfeb02JqjdIcl5ZXCdyZWjHnHN22jbPpvIAbcSA_Fe2BShrlThfPFzX3tYujQdFLpvO8rofJLALMLpvjQQscvevm-Uuzal8O4W574jBcCDqJbXuRup9BERT88V99ktUrfJ1VTuAlQasHuN9UELio-3xNbSIQz_YwjxJZsPXIZ4f-uXc9CGoXHcCtgT6EnmfYnRaS7xJc4vKgCncnRDmBx1nHhx-S-uARnc-eT2KU19C0oGWvY7jnlPm3BgXo7YMdYaGP-Ahot8eH7OBQ%3D%3D&sa=X&ved=2ahUKEwikmam7xe2GAxXl4skDHRugAawQ6RN6BAgPEAE&biw=1920&bih=991&dpr=1"
    ),
)

@Composable
fun DoctorCard(doctor: Doctor) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(5.dp)),
            painter = rememberAsyncImagePainter(doctor.image),
            contentDescription = doctor.name,
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
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(doctor.link))
                        context.startActivity(intent)
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = my_secondary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .shadow(elevation = 8.dp),
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = doctor.name,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = doctor.type,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DoctorSearchScreen() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val filteredDoctors = doctorItems.filter {
        it.name.contains(searchQuery.text, ignoreCase = true) ||
                it.type.contains(searchQuery.text, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        DoctorSearchBar(searchQuery) {
            searchQuery = it
        }
        Spacer(modifier = Modifier.height(8.dp))
        DoctorList(doctors = filteredDoctors)
    }
}

@Composable
fun DoctorSearchBar(searchQuery: TextFieldValue, onSearchQueryChange: (TextFieldValue) -> Unit) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        placeholder = { Text(text = "Search by name or specialty") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun DoctorList(doctors: List<Doctor>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        doctors.forEach { doctor ->
            DoctorCard(doctor = doctor)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
