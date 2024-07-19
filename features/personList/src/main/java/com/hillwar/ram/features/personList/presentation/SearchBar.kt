package com.hillwar.ram.features.personList.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.hillwar.ram.core.theme.ShadowElevation
import com.hillwar.ram.core.theme.TextPaddingHorizontal
import com.hillwar.ram.core.theme.TextPaddingVertical

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = EMPTY,
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(EMPTY) }
    var isHintDisplayed by remember { mutableStateOf(true) }

    Box(modifier = modifier) {
        SearchTextField(
            text = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            onFocusChanged = { isFocused ->
                isHintDisplayed = !isFocused && text.isEmpty()
            }
        )
        if (isHintDisplayed) {
            HintText(hint = hint)
        }
    }
}

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        maxLines = MAX_LINES,
        singleLine = true,
        textStyle = TextStyle(color = Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .shadow(ShadowElevation, CircleShape)
            .background(Color.White, CircleShape)
            .padding(horizontal = TextPaddingHorizontal, vertical = TextPaddingVertical)
            .onFocusChanged { state -> onFocusChanged(state.isFocused) }
            .testTag("SearchBar")
    )
}

@Composable
fun HintText(hint: String) {
    Text(
        text = hint,
        color = Color.LightGray,
        style = TextStyle(color = Color.Gray, textAlign = TextAlign.Start),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TextPaddingHorizontal, vertical = TextPaddingVertical)
    )
}
