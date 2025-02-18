package com.example.coffeeshop.screens.manager.employee

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.filled.client
import com.example.coffeeshop.data.user.ClientViewModel
import com.example.coffeeshop.data.user.ManagerUser
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedInputTextField
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter


@Composable
fun AddNewClientScreen(viewModel: ClientViewModel = viewModel()){

    val router = LocalRouter.current
    AddNewClientContent(
        onClick = { client ->
            viewModel.add(client)
            ManagerUser.login(client.email, client.name)
            router.launch(AppRoute.Menu.MenuScreen)
        }
    )
}



@Composable
fun AddNewClientContent(
    onClick: (User.Client) -> Unit = {},
) {
    val client by remember { mutableStateOf(User.Client()) }
    var name by remember { mutableStateOf(client.name) }
    var surname by remember { mutableStateOf(client.surname) }
    var email by remember { mutableStateOf(client.email) }
    var phoneNumber by remember { mutableStateOf(client.phoneNumber) }
    var password by remember { mutableStateOf(client.password) }
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
            CustomOutlinedInputTextField(
                value = name,
                onValueChange = { newText ->
                    name = newText
                },
                label = { Text(text = "Ім'я") }
            )
            CustomOutlinedInputTextField(
                value = surname,
                onValueChange = { newText ->
                    surname = newText
                },
                label = { Text(text = "Прізвище") }
            )
            CustomOutlinedInputTextField(
                value = email,
                onValueChange = { newText ->
                    email = newText
                },
                label = {Text(text = "Пошта")}
            )
            CustomOutlinedInputTextField(
                value = phoneNumber,
                onValueChange = { newText ->
                    phoneNumber = newText
                },
                label = { Text(text = "Номер телефону") }
            )
            CustomOutlinedInputTextField(
                value = password,
                onValueChange = { newText ->
                    password = newText
                },
                label = {Text(text = "Пароль")}
            )
        }
        Button(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End),
            onClick = {
                val newClient = client.copy(
                    name = name,
                    surname = surname,
                    email = email,
                    phoneNumber = phoneNumber,
                    password = password
                )
                onClick(newClient)

            },
            enabled = (password.length >= 6) and
                    email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$")) and
                    password.isNotEmpty() and
                    email.isNotEmpty(),
        ) {
        Text(text = "Зареєструвати працівника")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAddNewClientScreen() {
    CoffeeAppTheme(darkTheme = true){
        Surface {
            AddNewClientContent ()
        }
    }
}