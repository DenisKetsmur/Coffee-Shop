package com.example.coffeeshop.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.ManagerUser
import com.example.coffeeshop.scaffold.AppNavigationBar
import com.example.navigationmodule.LocalRouter

@Composable
fun LoginScreen(){
    val router = LocalRouter.current
    val context = LocalContext.current
    LoginContent(
        onLogin = { email, password ->
            if (ManagerUser.login(email, password)) {
                if (ManagerUser.isAdmin()) {
                    router.launch(AppRoute.StartUI.Menu)
                } else if (ManagerUser.isManager()) {
                    router.launch(AppRoute.StartUI.Menu)
                }else {
                    Toast.makeText(
                        context,
                        "Неправильний пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    )
}

@Composable
fun LoginContent(
    onLogin: (String, String) ->Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 180.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Авторизація",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
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
                        passwordError = password.length < 6 && password.isNotEmpty()
                    }
                },
        )

        if (passwordError and password.isNotEmpty()) {
            Text(
                text = "Пароль повинен бути не менше 6 символів",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onLogin(email, password)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = (password.length > 6) and
                    email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) and
                    password.isNotEmpty() and
                    email.isNotEmpty()
        ) {
            Text("Увійти")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewLoginScreen() {
    LoginScreen()
}