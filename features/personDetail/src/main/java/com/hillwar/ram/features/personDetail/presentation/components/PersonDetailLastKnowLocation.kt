package com.hillwar.ram.features.personDetail.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.theme.FontLarge
import com.hillwar.ram.core.theme.FontMedium
import com.hillwar.ram.core.theme.PaddingMedium
import com.hillwar.ram.features.personDetail.R

@Composable
fun PersonDetailLastKnowLocation(person: Person) {
    Text(
        text = stringResource(R.string.person_detail_last_know_location),
        fontWeight = FontWeight.Bold,
        fontSize = FontMedium,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium)
    )
    Text(
        text = person.location.name,
        fontSize = FontLarge,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium),
        maxLines = MAX_LINES,
        overflow = TextOverflow.Ellipsis
    )
}
