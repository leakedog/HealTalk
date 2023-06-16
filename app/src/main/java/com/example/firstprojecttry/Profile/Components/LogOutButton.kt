package com.example.firstprojecttry.Profile.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Login.AuthViewModel
import com.example.firstprojecttry.ui.theme.Purple80

@Preview
@Composable
fun LogOutButton() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {
            AuthViewModel.logOut();
        },
            modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
                .fillMaxWidth().background(Purple80)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Log out",
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .size(50.dp),
                    tint = Color.White
                )
                Text(
                    "Leave",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 15.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}