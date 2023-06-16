package com.example.firstprojecttry.Login

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.firstprojecttry.R

@Composable
fun SocialMediaIcons(
    size: Dp
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        AuthViewModel.handleActivityCode(result.resultCode)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            onClick = {
                launcher.launch(Intent(context, GoogleSignInActivity::class.java))
            },
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_logo),
                modifier = Modifier.size(size),
                contentDescription = "Go back from edit profile",
            )
        }

    }
}