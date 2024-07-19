package com.hillwar.ram.features.personDetail.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.theme.FontDetailLarge
import com.hillwar.ram.features.personDetail.R
import java.util.Locale

@Composable
fun PersonDetailName(person: Person) {
    Text(
        text = String.format(
            stringResource(R.string.person_detail_name_format),
            person.id,
            person.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        ),
        fontWeight = FontWeight.Bold,
        fontSize = FontDetailLarge,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onSurface
    )
}
