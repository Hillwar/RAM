package com.hillwar.ram.features.error

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hillwar.error.R
import com.hillwar.ram.core.theme.LightBlue
import com.hillwar.ram.core.theme.PaddingLarge

@Composable
fun ErrorScreen(
    navController: NavHostController
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error_view))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlue)
            .padding(PaddingLarge)
            .testTag("ErrorScreen"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth(),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        ErrorMessage(
            message = stringResource(R.string.something_went_wrong_try_later)
        )
        ErrorButton(
            onClick = {
                navController.popBackStack()
            }
        )
    }
}

@Composable
private fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(vertical = 24.dp),
        text = message,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun ErrorButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent
        ),
        onClick = onClick
    ) {
        Text(
            color = MaterialTheme.colorScheme.primary,
            text = stringResource(R.string.go_back)
        )
    }
}
