package com.example.coffeeshop.screens.shareScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter

@Composable
fun GeneralPageScreen() {
    val router = LocalRouter.current
    LazyColumn {
        item {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(R.mipmap.background),
                    contentDescription = stringResource(R.string.background_icon),
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.9f),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { router.launch(AppRoute.StartUI.Login) },
                    ) {
                        Text(
                            text = "Вхід",
                            fontSize = 20.sp,
                        )
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(50.dp),
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(200.dp))
                    Icon(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = stringResource(R.string.logo),
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 96.sp,
                        fontFamily = FontFamily(Font(R.font.comicoon_regular)),
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(300.dp),
                        lineHeight = 80.sp,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "ПРО НАС",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Високотехнологічна обробка відбірних кавових зерен, висока якість " +
                            "кави, оригінальні рецепти напоїв, індивідуальне обслуговування, " +
                            "неповторна атмосфера радості, щастя та тепла кав’ярні зроблять " +
                            "ваш відпочинок у Coffee Shop дійсно затишним.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    letterSpacing = 1.sp,
                    style = TextStyle(lineHeight = 24.sp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Рекомендовані Напої",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Light,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Column {
                        PointBeforeText("Американо")
                        PointBeforeText("Американо")
                        PointBeforeText("Американо")
                        PointBeforeText("Американо")
                        PointBeforeText("Американо")
                    }
                    Spacer(modifier = Modifier.width(52.dp))
                    Column {
                        PointBeforeText("Американо")
                        PointBeforeText("Американо")
                        PointBeforeText("Американо")
                        PointBeforeText("Американо")
                        PointBeforeText("Американо")
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Button(
                    shape = RoundedCornerShape(1.dp),
                    onClick = { router.launch(AppRoute.Menu.Menu) },
                    border = BorderStroke(1.dp, Color.Gray),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onSurface
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
    fontSize: TextUnit = 18.sp,
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
    CoffeeAppTheme {
        GeneralPageScreen()
    }
}