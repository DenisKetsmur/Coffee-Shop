package com.example.coffeeshop.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.navigationmodule.LocalRouter

@Composable
fun GeneralPageScreen() {
    val router = LocalRouter.current
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
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Рекомендовані Напої",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row{
                    Column {
                        PointBeforeText ("Американо" )
                        PointBeforeText ("Американо" )
                        PointBeforeText ("Американо" )
                        PointBeforeText ("Американо" )
                        PointBeforeText ("Американо" )
                    }
                    Spacer(modifier = Modifier.width(50.dp))
                    Column {
                        PointBeforeText ("Американо" )
                        PointBeforeText ("Американо" )
                        PointBeforeText ("Американо" )
                        PointBeforeText ("Американо" )
                        PointBeforeText ("Американо" )
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(end = 23.dp),
                contentAlignment = Alignment.CenterEnd,
            ){
                Button(
                    shape = RoundedCornerShape(1.dp),
                    onClick = {
                        router.launch(AppRoute.StartUI.Menu)
                    },
                    border = BorderStroke(1.dp, Color.Gray),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Gray
                    )
                ) {
                    Text(
                        text = "Детальніше про товари"
                    )
                }
            }
        }
    }
}

@Composable
fun PointBeforeText(
    text: String,
    fontSize: TextUnit = 20.sp,
){
    Row(
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = "•",
            modifier = Modifier.padding(end = 8.dp),
            fontSize = fontSize
        )
        Text(
            text = text,
            textAlign = TextAlign.Justify,
            fontSize = fontSize
        )
    }
}


@Preview(showBackground = true, heightDp = 1500)
@Composable
private fun PreviewGeneralPageScreen() {
    GeneralPageScreen()
}