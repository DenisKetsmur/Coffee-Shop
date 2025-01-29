package com.example.coffeeshop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.R

@Composable
fun GeneralPageScreen() {

    LazyColumn {
        item {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.mipmap.background),
                    contentDescription = stringResource(R.string.background_icon),
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.9f),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = stringResource(R.string.logo),
                        tint = Color.Unspecified,
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.Black,
                        fontSize = 43.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ПРО НАС",
                    color = Color.Black,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Високотехнологічна обробка відбірних кавових зерен, висока якість " +
                            "кави, оригінальні рецепти напоїв, індивідуальне обслуговування, " +
                            "неповторна атмосфера радості, щастя та тепла кав’ярні зроблять " +
                            "ваш відпочинок у Coffee Shop дійсно затишним.",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    letterSpacing = 1.sp,
                    style = TextStyle(lineHeight = 24.sp)
                )
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewGeneralPageScreen() {
    GeneralPageScreen()
}