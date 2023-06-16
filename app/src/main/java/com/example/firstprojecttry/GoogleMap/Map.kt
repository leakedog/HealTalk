package com.example.firstprojecttry.GoogleMap

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField
import com.example.firstprojecttry.Logic.Executor
import com.example.firstprojecttry.Profile.ProfileViewModel
import com.example.firstprojecttry.helperActivities.BottomBar
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun DisplayAllMarkers(marks: List<Executor>, updateStatus: (Executor) -> Unit){
    for((id, executor) in marks.withIndex()){
        var checkedState by remember { mutableStateOf(false) }
        if(checkedState){
            updateStatus(executor)
            checkedState = false
        }

        if (executor.location == null) continue

        Marker(
            state = MarkerState(position = LatLng(executor.location.lat, executor.location.lng)),
            onClick = {
                checkedState = true
                true
            },
            icon = getBitMapFromText(DescriptionCharacteristicField.getFieldValue("price", executor).toString() + "$")
        )
    }
}


var center = CameraPosition.fromLatLngZoom(LatLng(50.0614285,19.9209253), 17f)
@Composable
fun ShowMap() {
    var showId by remember { mutableStateOf(Executor()) }
    val showExecutor = remember { mutableStateOf(false) }
    val displayCard = {a: Executor -> showId = a
        showExecutor.value = true
    }

    val cameraPositionState = rememberCameraPositionState {
        position = center
    }
    center = cameraPositionState.position
    Scaffold(
        bottomBar = {
            BottomBar()
        }
    ) {
            paddingValues ->
        GoogleMap(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true, mapType = MapType.TERRAIN)
        ) {
            if (showExecutor.value) {
                ProfileViewModel.showExecutor(showId)
            } else{
                val executors = ArrayList<Executor>();
                for (executor in Executor.container.container) {
                    executors.add(executor.value)
                }
                DisplayAllMarkers(executors, displayCard)
            }
        }
    }
}