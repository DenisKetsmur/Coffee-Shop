package com.example.coffeeshop.screens.cardForScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalModifierNode
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun CustomOutlinedInputTextField(
    value:String,
    onValueChange: (String) -> Unit,
    singleLine:Boolean = false,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (()-> Unit)? = null,
    trailingIcon: @Composable (()-> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done
    ),
    visualTransformation: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    OutlinedTextField(
        value = value,
        onValueChange = { newValueName ->
            onValueChange(newValueName)
        },
        singleLine = singleLine,
        label = label,
        placeholder = placeholder,
        trailingIcon = trailingIcon,
        visualTransformation = if(visualTransformation) DateTransformation() else VisualTransformation.None,
        modifier = modifier.focusRequester(focusRequester),
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
    )
}

class DateTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return filterText(text)
    }
}

fun filterText(text: AnnotatedString): TransformedText {
    val original:String = text.text

    val trimmed = if (original.length >= 2) original.substring(0, minOf(3, original.length)) else original

    val out = if (trimmed.contains("/")) trimmed
    else if (trimmed.isNotEmpty()) "${trimmed[0]}/${trimmed.drop(1)}"
    else ""

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when {
                offset == 0 -> 0
                offset == 1 -> 2
                else -> 3
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                offset <= 1 -> offset
                offset == 2 -> 1
                else -> 2
            }
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}




@Preview(showBackground = true)
@Composable
private fun PreviewCustomOutlinedTextField(){
    MaterialTheme{
        Surface {
            CustomOutlinedSearchTextField()
        }
    }
}