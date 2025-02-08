package com.example.coffeeshop.screens.Manager

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.screens.CardForScreens.CustomOutlinedTextField

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddNewClientScreen() {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomOutlinedTextField(
//                searchText = searchText,
//                onTextChange = {newText -> searchText = newText},
                label = stringResource(R.string.name_and_surname_client)
            )
            CustomOutlinedTextField(
//                searchText = searchText,
//                onTextChange = { newText -> searchText = newText },
                label = stringResource(R.string.telephone_number_client)
            )
        }
        Button(
            modifier = Modifier.padding(16.dp).align(Alignment.End),
            onClick = {
                TODO()
            },
//            enabled = (password.length >= 6) and
//                    email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$")) and
//                    password.isNotEmpty() and
//                    email.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary, // Основний колір кнопки
                contentColor = MaterialTheme.colorScheme.onSecondary, // Колір тексту та іконок
                disabledContainerColor = MaterialTheme.colorScheme.secondary, // Колір кнопки у неактивному стані
                disabledContentColor = MaterialTheme.colorScheme.onSecondary // Колір тексту у неактивному стані
            )
        ) {
        Text(text = "Зареєструвати користувача")
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewAddNewClientScreen() {
    AddNewClientScreen()
}