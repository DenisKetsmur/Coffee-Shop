package com.example.coffeeshop.screens


import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.R



@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(377.07.dp, 290.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(colorResource(R.color.primary))
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .size(377.07.dp, 63.2.dp)
                        .background(colorResource(R.color.secondary)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "ВХІД",
                        fontSize = 50.sp,
                        color = colorResource(R.color.background)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    BasicTextField(
                        value = username,
                        onValueChange = { username = it },
                        modifier = Modifier
                            .size(height = 49.dp, width = 348.55.dp)
                            .background(colorResource(R.color.background), RoundedCornerShape(20.dp)),
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 25.sp,
                            color = colorResource(R.color.text),
                            textAlign = TextAlign.Center
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                    ){
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .padding(start = 20.dp),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Text(
                                text = if (username.isEmpty()) "Пошта" else username,
                                fontSize = 18.sp,
                                color = colorResource(R.color.text)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(23.dp))
                    BasicTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .size(height = 49.dp, width = 348.55.dp)
                            .background(colorResource(R.color.background), RoundedCornerShape(20.dp)),
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 25.sp,
                            color = colorResource(R.color.text),
                            textAlign = TextAlign.Center
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                    ){
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .padding(start = 20.dp),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Text(
                                text = if (password.isEmpty()) "Пароль" else password,
                                fontSize = 18.sp,
                                color = colorResource(R.color.text)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 159.dp, height = 48.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(colorResource(R.color.secondary)),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "Увійти",
                                fontSize = 32.sp,

                            )
                        }
                    }
                }
            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
private fun PreviewLoginScreen() {
    LoginScreen()
}