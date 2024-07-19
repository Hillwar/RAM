package com.hillwar.ram.features.personDetail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.theme.SpacerSmall
import com.hillwar.ram.features.personDetail.presentation.components.PersonDetailEpisodes
import com.hillwar.ram.features.personDetail.presentation.components.PersonDetailLastKnowLocation
import com.hillwar.ram.features.personDetail.presentation.components.PersonDetailName
import com.hillwar.ram.features.personDetail.presentation.components.PersonDetailSpecies
import com.hillwar.ram.features.personDetail.presentation.components.PersonDetailStatus

@Composable
fun PersonDetailSection(
    person: Person, modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(top = PersonDetailScreenDefaults.PERSON_IMAGE_SIZE / 2)
    ) {
        PersonDetailName(person)
        Spacer(modifier = Modifier.height(SpacerSmall))
        PersonDetailStatus(person)
        Spacer(modifier = Modifier.height(SpacerSmall))
        PersonDetailSpecies(person)
        Spacer(modifier = Modifier.height(SpacerSmall))
        PersonDetailLastKnowLocation(person)
        Spacer(modifier = Modifier.height(SpacerSmall))
        PersonDetailEpisodes(person)
    }
}
