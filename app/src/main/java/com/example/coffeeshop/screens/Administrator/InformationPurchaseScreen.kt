package com.example.coffeeshop.screens.Administrator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breens.beetablescompose.BeeTablesCompose
import com.example.coffeeshop.data.supplier.Supplier


@Composable
fun InformationPurchaseScreen() {
    InformationPurchaseContent(supplierDataList[0])
}



@Composable
fun InformationPurchaseContent(
    supplier: Supplier
){
    LazyColumn {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Інформація",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = supplier.nameCompany,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Телефон: ${supplier.phoneNumber}",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Пошта: ${supplier.email}",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 20.sp
                        )
                    }
                }
            }
            val headerTitles = listOf("Назва", "Ціна", "Кількість")

            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp)
            ){
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewInformationPurchaseScreen(){
    InformationPurchaseScreen()
}


