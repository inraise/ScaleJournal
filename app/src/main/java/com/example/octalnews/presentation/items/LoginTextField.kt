package com.example.octalnews.presentation.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.octalnews.presentation.theme.fontInter
import java.util.regex.Pattern

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun loginTextField(text: String, placeholder: String): String {
    var textR by remember {
        mutableStateOf(text)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(15.dp)),
        value = textR,
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFF303030),
                fontFamily = fontInter,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        },
        onValueChange = { textR = it },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFFEF0E6),
            textColor = Color(0xFF303030),
            cursorColor = Color(0xFF303030),
            focusedIndicatorColor = Color(0xFFFEF0E6)
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = if ((Pattern.matches(
                    "^[A-Za-z0-9+_.-]+@[a-z]+[.]+[a-z]+[a-z]",
                    textR
                )
                        && placeholder != "Enter your password") || (
                        placeholder == "Enter your password" &&
                                textR.length >= 8
                        )
            ) ImeAction.Done else ImeAction.None
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() })
    )

    return textR
}