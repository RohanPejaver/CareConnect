package com.example.careconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.careconnect.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HospitalMap() {

    // val context = LocalContext.current
    // val userLocation = getUserLocation(context)
    // var location = LatLng(userLocation.latitude, userLocation.longitude)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(30.626230, -97.688210), 7f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(300.dp)
            .clip(RoundedCornerShape(15.dp)),
        cameraPositionState = cameraPositionState,


    ) {
        Clustering(
            items = markersData,
            onClusterClick = {
                cameraPositionState.move(
                    update = CameraUpdateFactory.zoomIn()
                )
                true
            },
            onClusterItemClick = {
                false
            },
            clusterItemContent = {
                Image(
                    painter = painterResource(id = R.drawable.hospital_pin),
                    contentDescription = "",
                    modifier = Modifier
                        .height(32.dp)
                        .width(45.dp)
                )
            },
            clusterContent = {
                Image(
                    painter = painterResource(id = R.drawable.hospital_pin),
                    contentDescription = "",
                    modifier = Modifier
                        .height(64.dp)
                        .width(90.dp)
                )
            }
        )
    }
}

data class MarkerData(
    val location: LatLng,
    val name: String,
    val iconResourceId: Int,
    val description: String?,
    ) : ClusterItem {
    override fun getPosition(): LatLng = location
    override fun getTitle(): String? = name
    override fun getSnippet(): String? = description
    override fun getZIndex(): Float? = 1f
}

private val markersData = listOf(
    MarkerData(
        location = LatLng(30.520600, -97.694270),
        name = "Mrudula Deshpande M.D.",
        iconResourceId = R.drawable.hospital_pin,
        description = null,
    ),
    MarkerData(
        location = LatLng(30.528810, -97.762120),
        name = "Cedar Park Regional Medical Center",
        iconResourceId = R.drawable.hospital_pin,
        description = null,
    ),
    MarkerData(
        location = LatLng(30.626230, -97.688210),
        name = "St. David's Georgetown Hospital",
        iconResourceId = R.drawable.hospital_pin,
        description = null,
    ),
    MarkerData(
        location = LatLng(30.563520, -97.684631),
        name = "Baylor Scott & White Medical Center",
        iconResourceId = R.drawable.hospital_pin,
        description = null,
    ),
)
