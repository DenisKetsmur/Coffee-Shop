package com.example.coffeeshop.screens.CardForScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.supplier.Supplier
import com.example.coffeeshop.screens.Administrator.supplierDataList
import com.example.navigationmodule.LocalRouter

@Composable
fun CardSupplier(
    supplier: Supplier,
    onClick:()->Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row{
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier 
                    .clip(RoundedCornerShape(16.dp))
                    .border(2.dp, Color.Gray)
            ){
                Image(
                    painter =supplier.photo ?: painterResource(R.mipmap.face_photo),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                )
            }
            Column(
                modifier = Modifier.weight(2f)
                    .padding(start = 10.dp, top = 5.dp)
            ) {
                Text(
                    text = supplier.nameCompany,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Категорії для замовлень: ${supplier.getCategoryList(supplierDataList[0])}",
                    style = TextStyle(lineHeight = 23.sp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewCardSupplier(){
    val router = LocalRouter.current
    LazyColumn {
        item{
            CardSupplier(supplierDataList[0], onClick = {router.launch(AppRoute.Administrator.Purchase.InformationPurchase)})
            CardSupplier(supplierDataList[0], onClick = {router.launch(AppRoute.Administrator.Purchase.InformationPurchase)})
            CardSupplier(supplierDataList[0], onClick = {router.launch(AppRoute.Administrator.Purchase.InformationPurchase)})
            CardSupplier(supplierDataList[0], onClick = {router.launch(AppRoute.Administrator.Purchase.InformationPurchase)})
            CardSupplier(supplierDataList[0], onClick = {router.launch(AppRoute.Administrator.Purchase.InformationPurchase)})

        }
    }
}