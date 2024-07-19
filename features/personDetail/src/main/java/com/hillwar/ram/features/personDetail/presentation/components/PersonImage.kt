package com.hillwar.ram.features.personDetail.presentation.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.theme.PROGRESS_BAR_SCALE
import com.hillwar.ram.features.personDetail.presentation.PersonDetailScreenDefaults

@Composable
fun PersonImage(person: Person?) {
    person?.image?.let {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(it)
                .crossfade(true)
                .build(),
            loading = {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.scale(PROGRESS_BAR_SCALE)
                )
            },
            contentDescription = person.name,
            modifier = Modifier
                .size(PersonDetailScreenDefaults.PERSON_IMAGE_SIZE)
                .offset(y = PersonDetailScreenDefaults.TOP_PADDING)
                .shadow(PersonDetailScreenDefaults.SHADOW_ELEVATION, CircleShape)
                .clip(CircleShape)
        )
    }
}
