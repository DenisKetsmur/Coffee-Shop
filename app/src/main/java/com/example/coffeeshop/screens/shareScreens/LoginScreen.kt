package com.example.coffeeshop.screens.shareScreens


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.roomDone.clients.ClientViewModel
import com.example.coffeeshop.data.roomDone.clients.room.entities.Position
import com.example.coffeeshop.data.roomDone.employee.EmployeeViewModel
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent

@Composable
fun LoginScreen(
    viewModelClient: ClientViewModel,
    viewModelEmployee: EmployeeViewModel
){
    val router = LocalRouter.current
    val context = LocalContext.current

    var errorMessage by remember { mutableStateOf<String?>(null) }
    LoginContent(
        onLogin = { email, password ->
            viewModelClient.viewModelScope.launch {
                val client = viewModelClient.findClientByEmail(email)
                if (client != null) {
                    if (client.password == password) {
                        viewModelClient.setCurrentClient(client)
                        router.launch(AppRoute.Menu.MenuScreen)
                    } else {
                        errorMessage = "Невірний пароль"
                    }
                } else {
                    val employee = viewModelEmployee.findEmployeeByEmail(email)
                    if (employee != null) {
                        if (employee.password == password) {
                            viewModelEmployee.setCurrentEmployee(employee)
                            router.launch(AppRoute.Menu.MenuScreen)
                        } else {
                            errorMessage = "Невірний пароль"
                        }
                    } else {
                        errorMessage = "Користувача з таким email не знайдено"
                    }
                }
            }
        },
        toRegister = {
            router.launch(AppRoute.StartUI.Registration)
        }
    )
}

@Composable
fun LoginContent(
    toRegister: ()-> Unit,
    onLogin: (String, String) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(52.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            singleLine = true,
            label = { Text("Електронна пошта") },
            isError = emailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (focusState.hasFocus) {
                        emailError = false
                    }else{
                        emailError = !email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) and email.isNotEmpty()
                    }
                },

        )

        if (emailError and email.isNotEmpty()) {
            Text(
                text = "Неправильний формат пошти",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text("Пароль") },
            isError = passwordError,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (focusState.hasFocus) {
                        passwordError = false
                    }else{
                        passwordError = password.length <= 6 && password.isNotEmpty()
                    }
                },
        )

        if (passwordError and password.isNotEmpty()) {
            Text(
                text = "Пароль повинен бути не менше 6 символів",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Box(
                modifier = Modifier.weight(1f)
                    .clickable {
                        toRegister()
                    },
            ) {
                Text("Зареєеструватися")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    onLogin(email, password)
                },
                modifier = Modifier.weight(1f),
                enabled = (password.length >= 6) and
                        email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$")) and
                        password.isNotEmpty() and
                        email.isNotEmpty(),
            ) {
                Text("Увійти")
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewLoginScreen() {
    CoffeeAppTheme {
        Surface {
            LoginContent({},{it,it2->})
        }
    }
}