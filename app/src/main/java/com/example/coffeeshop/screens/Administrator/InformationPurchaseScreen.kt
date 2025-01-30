package com.example.coffeeshop.screens.Administrator

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InformationPurchaseScreen(){

}

@Composable
fun InformationPurchase(
    supplier: Supplier
){
    Column {
        Text(
            text = "Інформація"
        )
        Text(
            text = """
                |Tелефон: ${supplier.phoneNumber}
                |Пошта: ${supplier.email}
            """.trimMargin()
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewInformationPurchaseScreen(){
    InformationPurchaseScreen()
}
