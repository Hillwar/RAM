package com.hillwar.ram.features.personDetail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.hillwar.ram.core.extensions.utils.getStatusColor
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.theme.FontLarge
import com.hillwar.ram.core.theme.FontMedium
import com.hillwar.ram.core.theme.PaddingMedium
import com.hillwar.ram.core.theme.PaddingSmall
import com.hillwar.ram.core.theme.PersonStatusSize
import com.hillwar.ram.core.theme.Zero
import com.hillwar.ram.features.personDetail.R

const val MAX_LINES = 1

@Composable
fun PersonDetailStatus(person: Person) {
    Row {
        Box(
            modifier = Modifier
                .padding(PaddingMedium, PaddingSmall, Zero, Zero)
                .size(PersonStatusSize)
                .clip(CircleShape)
                .background(person.status.getStatusColor())
        )
        Text(
            text = stringResource(R.string.person_detail_status),
            fontWeight = FontWeight.Bold,
            fontSize = FontMedium,
            textAlign = TextAlign.Start,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium)
        )
    }
    Text(
        text = person.status,
        fontSize = FontLarge,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium),
        maxLines = MAX_LINES,
        overflow = TextOverflow.Ellipsis,
        color = Color.Gray
    )
}
