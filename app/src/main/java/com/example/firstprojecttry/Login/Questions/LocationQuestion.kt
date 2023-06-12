package com.example.firstprojecttry.Login.Questions

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstprojecttry.Logic.Location
import com.example.firstprojecttry.Logic.UtilityClass.descriptionMap
import com.example.firstprojecttry.Logic.UtilityClass.descriptionStates
import com.example.firstprojecttry.R
import com.example.firstprojecttry.center
import com.example.firstprojecttry.getBitMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Preview
@Composable
fun LocationQuestion(
    name: String = "location"
) {

    val field = descriptionMap[name]!!

    var curPos by remember { mutableStateOf(center.target) }

    val cameraPositionState = rememberCameraPositionState {
        position = center
    }

    curPos = cameraPositionState.position.target

    descriptionStates[name] = Location(
        cameraPositionState.position.target.latitude,
        cameraPositionState.position.target.longitude
    )

    GoogleMap(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true),
        onMyLocationClick = { position ->
            curPos = LatLng(position.latitude, position.longitude)
        },
        onMapClick = { position ->
            curPos = position
        }
    ) {
        Marker(
            state = MarkerState(position = curPos),
            onClick = { mar ->
                true
            },
            icon = getBitMap(context = LocalContext.current, R.drawable.output_onlinepngtools)
        )
    }

}